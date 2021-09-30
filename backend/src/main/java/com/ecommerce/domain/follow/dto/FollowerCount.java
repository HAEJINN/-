package com.ecommerce.domain.follow.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowerCount {

    private long count;

    public FollowerCount(final long count) {
        this.count = count;
    }

}
