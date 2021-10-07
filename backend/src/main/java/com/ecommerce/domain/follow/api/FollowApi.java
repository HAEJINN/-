package com.ecommerce.domain.follow.api;

import com.ecommerce.domain.follow.application.FollowService;
import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowApi {

    private final FollowService followService;

    @GetMapping("/api/v1/follow/{userId}/count")
    public ResponseEntity<FollowCount> countFollower(@PathVariable final Long userId) {
        final long followerCount = followService.countFollower(userId);
        final long followingCount = followService.countFollowing(userId);
        final FollowCount followCount = new FollowCount(followerCount, followingCount);
        return ResponseEntity.ok().body(followCount);
    }

    @GetMapping("/api/v1/follow/{id}/followable")
    public ResponseEntity<Boolean> followable(@AuthenticationPrincipal final String email, @PathVariable Long id) {
        boolean followable = followService.followable(email, id);
        return ResponseEntity.ok().body(followable);
    }

    @GetMapping("/api/v1/follow/followers")
    public ResponseEntity<List<FollowerListResponse>> findMyFollowers(@AuthenticationPrincipal final String email) {
        final List<FollowerListResponse> followers = followService.findMyFollowers(email);
        return ResponseEntity.ok().body(followers);
    }

    @GetMapping("/api/v1/follow/followings")
    public ResponseEntity<List<FollowingListResponse>> findMyFollowings(@AuthenticationPrincipal final String email) {
        final List<FollowingListResponse> followings = followService.findMyFollowings(email);
        return ResponseEntity.ok().body(followings);
    }

    @GetMapping("/api/v1/follow/followers/{id}")
    public ResponseEntity<List<FollowerListResponse>> findFollowers(@PathVariable Long id) {
        final List<FollowerListResponse> followers = followService.findFollowers(id);
        return ResponseEntity.ok().body(followers);
    }

    @GetMapping("/api/v1/follow/followings/{id}")
    public ResponseEntity<List<FollowingListResponse>> findFollowings(@PathVariable Long id) {
        List<FollowingListResponse> followings = followService.findFollowings(id);
        return ResponseEntity.ok().body(followings);
    }

    @PostMapping("/api/v1/follow/followings/{id}")
    public ResponseEntity<FollowingSaveResponse> saveFollowings(@AuthenticationPrincipal final String email, @PathVariable Long id) {
        final Follow follow = followService.saveMyFollowing(email, id);
        return ResponseEntity.ok().body(FollowingSaveResponse.ofFollow(follow));
    }

}
