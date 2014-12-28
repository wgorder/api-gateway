package com.acme.demo.auth.user

import com.acme.demo.auth.authority.Authority
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.EqualsAndHashCode
import org.hibernate.validator.constraints.Email
import org.joda.time.DateTime

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Entity representing a user
 *
 * @author William Gorder
 * @since 12/30/14
 */
@EqualsAndHashCode(includes = ["email"])
class AcmeUser implements Serializable{

    private static final long serialVersionUID = 1L;

    Long userId

    @Email
    String email

    @JsonIgnore
    String password

    @Size(min = 0, max = 50)
    String firstName

    @Size(min = 0, max = 50)
    String lastName

    boolean activated = false;

    @Size(min = 0, max = 20)
    String activationKey

    @JsonIgnore
    Set<Authority> authorities = new HashSet<>()

    @NotNull
    String createdBy

    @NotNull
    DateTime createdDate = DateTime.now()

    String lastModifiedBy

    DateTime lastModifiedDate = DateTime.now()
}
