package com.acme.demo.auth.authority

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Defines the granted authorities currently held by an AcmeUser
 *
 * @author William Gorder
 * @since 12/30/14
 */
@EqualsAndHashCode
@ToString
class Authority implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 0, max = 50)
    String name;

    @Override
    String getAuthority() {
        return new SimpleGrantedAuthority(name)
    }
}
