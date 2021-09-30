package com.ecommerce.domain.follow.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    long countByFollower(User follower);
    long countByFollowing(User following);
}
