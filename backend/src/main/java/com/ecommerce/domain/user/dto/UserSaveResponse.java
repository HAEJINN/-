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

    private Long id;
    private String email;
    private String profileImage;
    private String name;
    private UserStatus status;

    public UserSaveResponse(final User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.name = user.getName();
        this.status = user.getStatus();
    }

}

