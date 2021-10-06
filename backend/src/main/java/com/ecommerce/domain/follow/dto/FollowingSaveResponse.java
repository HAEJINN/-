package com.ecommerce.domain.follow.dto;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowingSaveResponse {

    private Long id;
    private String name;

    public static FollowingSaveResponse ofFollow(final Follow follow) {
        return ofUser(follow.getFollowing());
    }

    private static FollowingSaveResponse ofUser(final User user) {
        return new FollowingSaveResponse(user.getId(), user.getName());
    }

    public FollowingSaveResponse(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

}
