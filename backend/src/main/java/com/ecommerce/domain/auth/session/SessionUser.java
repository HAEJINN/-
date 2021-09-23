package com.ecommerce.domain.auth.session;

import com.ecommerce.domain.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this(user.getName(), user.getEmail(), user.getPhoto().getPath());
    }

    private SessionUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

}
