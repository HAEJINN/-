package com.ecommerce.domain.follow.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    long countByFollower(User follower);
    long countByFollowing(User following);

    @Query("select f from Follow f join fetch f.follower join fetch f.following join fetch f.follower.photo where f.following = :me")
    List<Follow> findMyFollowers(User me);

    @Query("select f from Follow f join fetch f.follower join fetch f.following join fetch f.following.photo where f.follower = :me")
    List<Follow> findMyFollowings(User me);

}
