package com.acme.demo.gateway

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

/**
 * Use Spring Session to manage our sessions across micro services.  Also has the
 * advantage of being easily horizontally scalable, no need to mess around with sticky sessions etc.
 * We will use this to manage CSRF tokens and cart data, since users can add items to a cart without being
 * logged in.
 *
 * @author William Gorder
 * @since 12/30/2014
 */
@Configuration
@EnableRedisHttpSession
class SessionConfiguration {

    @Autowired
    Environment environment

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory()
        jedisConnectionFactory.setHostName(environment.getProperty("app.redis.host"))
        return jedisConnectionFactory
    }

}
