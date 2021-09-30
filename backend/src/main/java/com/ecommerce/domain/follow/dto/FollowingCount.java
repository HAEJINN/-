package com.ecommerce.domain.follow.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowingCount {

    private long count;

    public FollowingCount(final long count) {
        this.count = count;
    }

}
