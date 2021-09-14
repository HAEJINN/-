package com.ecommerce.domain.user.dto;

import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSaveResponse {

    private Long id;
    private String name;
    private String email;
    private String password;

    public UserSaveResponse(final User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    private UserSaveResponse(final Long id, final String name, final String email, final String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}