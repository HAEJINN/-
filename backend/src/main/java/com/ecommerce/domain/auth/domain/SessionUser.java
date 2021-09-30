package com.ecommerce.domain.auth.domain;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserStatus;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String email;
    private String name;
    private UserStatus status;

    public SessionUser(final User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.status = user.getStatus();
    }

}
