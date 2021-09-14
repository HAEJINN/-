package com.ecommerce.domain.user.dto;

import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long id;
    private String name;
    private String email;

    public LoginResponse(final User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

    private LoginResponse(final Long id, final String name, final String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}