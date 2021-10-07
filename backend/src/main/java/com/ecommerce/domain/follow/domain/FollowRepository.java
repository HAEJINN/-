package com.ecommerce.domain.follow.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    long countByFollower(User follower);
    long countByFollowing(User following);

    @EntityGraph(attributePaths = {"follower", "following"})
    List<Follow> findByFollowing(User following);

    @EntityGraph(attributePaths = {"follower", "following"})
    List<Follow> findByFollower(User follower);

    Optional<Follow> findByFollowerAndFollowing(User follower, User following);

    boolean existsByFollowerAndFollowing(User follower, User following);

}
