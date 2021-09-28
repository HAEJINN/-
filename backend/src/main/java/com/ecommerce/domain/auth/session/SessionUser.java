package com.ecommerce.domain.auth.session;

import com.ecommerce.domain.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

public class SessionUser implements Serializable {

    public static final String SESSION_KEY = "user";

    private final String name;
    private final String email;
    private final String picture;

    public SessionUser(User user) {
        this(user.getName(), user.getEmail(), user.getPhoto().getPath());
    }

    private SessionUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return "name=" + name + "&email=" + email + "&picture=" + picture;
    }

}
