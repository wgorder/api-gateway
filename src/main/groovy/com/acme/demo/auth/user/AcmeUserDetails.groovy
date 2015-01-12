package com.acme.demo.auth.user

import org.springframework.security.core.userdetails.UserDetails

/**
 * User Details implementation for an AcmeUser
 *
 * @author William Gorder
 * @since 12/30/2014
 */
class AcmeUserDetails extends AcmeUser implements UserDetails {

    private static final long serialVersionUID = 1L;


    @Override
    String getUsername() {
        return getEmail()
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return isActivated()
    }
}
