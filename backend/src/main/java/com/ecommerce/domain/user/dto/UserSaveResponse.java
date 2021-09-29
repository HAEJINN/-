package com.ecommerce.domain.user.dto;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class UserSaveResponse {

    private String email;
    private String name;
    private UserStatus status;

    public UserSaveResponse(final User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.status = user.getStatus();
    }

}

