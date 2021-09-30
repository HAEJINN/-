package com.ecommerce.domain.follow.dto;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowerSaveResponse {

    private Long id;
    private String name;

    public static FollowerSaveResponse ofFollow(final Follow follow) {
        return ofUser(follow.getFollower());
    }

    private static FollowerSaveResponse ofUser(final User user) {
        return new FollowerSaveResponse(user.getId(), user.getName());
    }

    private FollowerSaveResponse(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

}
