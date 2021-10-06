package com.ecommerce.domain.follow.dto;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowingListResponse {

    private Long id;
    private String name;
    private String photo;

    public static FollowingListResponse ofFollow(final Follow follow) {
        return ofUser(follow.getFollowing());
    }

    private static FollowingListResponse ofUser(final User user) {
        return new FollowingListResponse(user.getId(), user.getName(), user.getProfileImage());
    }

    public FollowingListResponse(final Long id, final String name, final String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

}
