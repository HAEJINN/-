package com.ecommerce.domain.auth.session;

import com.ecommerce.domain.user.domain.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this(user.getName(), user.getEmail(), user.getPhoto().getPath());
    }

    public SessionUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

}
