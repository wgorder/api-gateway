package com.acme.demo.auth.authority;

/**
 * Constants for Spring Security authorities.
 *
 * @author William Gorder
 * @since 12/30/14
 */
public final class Authorities {

    private Authorities() {
    }

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
