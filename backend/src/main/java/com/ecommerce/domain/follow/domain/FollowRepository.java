package com.ecommerce.domain.follow.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    long countByFollower(User follower);
    long countByFollowing(User following);

    @EntityGraph(attributePaths = "Follow.follower.photo")
    @Query("select f from Follow f join fetch f.follower join fetch f.following where f.following = :following")
    List<Follow> findMyFollowers(@Param("following") User following);

    @EntityGraph(attributePaths = "Follow.following.photo")
    @Query("select f from Follow f join fetch f.follower join fetch f.following where f.follower = :follower")
    List<Follow> findMyFollowings(@Param("follower") User follower);

}
