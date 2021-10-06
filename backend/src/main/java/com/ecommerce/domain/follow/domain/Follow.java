package com.ecommerce.domain.follow.domain;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follows")
@Entity
public class Follow extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @Builder
    public Follow(final Long id, final User following, final User follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }

    public Follow changeFollowing(final User following) {
        this.following = following;
        return this;
    }

    public Follow changeFollower(final User follower) {
        this.follower = follower;
        return this;
    }

}
