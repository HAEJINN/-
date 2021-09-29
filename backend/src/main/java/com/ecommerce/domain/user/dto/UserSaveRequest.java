package com.ecommerce.domain.user.dto;

import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class UserSaveRequest {

    private String email;
    private String name;
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }


}

