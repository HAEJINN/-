package com.ecommerce.domain.follow.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowCount {

    private long followerCount;
    private long followingCount;

    public FollowCount(final long followerCount, final long followingCount) {
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

}
