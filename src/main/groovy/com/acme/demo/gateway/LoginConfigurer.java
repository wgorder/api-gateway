package com.acme.demo.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.Http401AuthenticationEntryPoint;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Configures permit all on all endpoints, just so that the Oauth-token relay works correctly with the
 * Zuul Proxy.  Each application manages its own authorization, however everything delegates to the auth-server
 * for authentication.
 *
 * @author William Gorder
 * @since 12/30/2014
 */
@Configuration
@EnableOAuth2Sso
@EnableZuulProxy
public class LoginConfigurer extends OAuth2SsoConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginConfigurer.class);

    @Override
    public void match(RequestMatchers matchers) {
        matchers.antMatchers("/**");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        new Http401AuthenticationEntryPoint(
                                "Session realm=\"SESSION\""))
                .and()
                .authorizeRequests().antMatchers("/", "/login", "/assets/**", "/bower_components/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    }

    /**
     * We want to add each CSRF token to the response as a Header.  We do this rather than the
     * Cookie (which Angular has built in support for).  The reason for this is we are using
     * spring-session which is persistent across browser tabs.  If we were to use the cookie approach
     * and the user opened multiple tabs CSRF tokens in one tab would be invalid in others.  See Rob Winch's comments
     * here for more details:
     *
     * https://github.com/jhipster/generator-jhipster/issues/363
     *
     *
     * @return
     */
    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class
                        .getName());
                if(null != token) {
                    // Spring Security will allow the Token to be included in this header name
                    response.setHeader("X-CSRF-HEADER", token.getHeaderName());
                    // Spring Security will allow the token to be included in this parameter name
                    response.setHeader("X-CSRF-PARAM", token.getParameterName());
                    // this is the value of the token to be included as either a header or an HTTP parameter
                    response.setHeader("X-CSRF-TOKEN", token.getToken());
                    filterChain.doFilter(request, response);
                }
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}