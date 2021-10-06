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
public class UserFindListResponse {

    private Long id;
    private String email;
    private String name;
    private UserStatus status;

    public UserFindListResponse(final User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.status = user.getStatus();
    }

}
