package com.ecommerce.domain.follow.api;

import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.follow.application.FollowService;
import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowApi {

    private final FollowService followService;

    @GetMapping("/api/v1/follow/followers/count")
    public ResponseEntity<FollowerCount> countFollower(@AuthenticationPrincipal final String email) {
        final long count = followService.countFollower(email);
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

    @GetMapping("/api/v1/follow/followings/count")
    public ResponseEntity<FollowerCount> countFollowing(@AuthenticationPrincipal final String email) {
        final long count = followService.countFollowing(email);
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

    @GetMapping("/api/v1/follow/followers")
    public ResponseEntity<List<FollowerListResponse>> findFollowers(@AuthenticationPrincipal final String email) {
        final List<FollowerListResponse> followers = followService.findFollowers(email);
        return ResponseEntity.ok().body(followers);
    }

    @GetMapping("/api/v1/follow/followings")
    public ResponseEntity<List<FollowingListResponse>> findFollowings(@AuthenticationPrincipal final String email) {
        final List<FollowingListResponse> followings = followService.findFollowings(email);
        return ResponseEntity.ok().body(followings);
    }

    @PostMapping("/api/v1/follow/followers/{id}")
    public ResponseEntity<FollowerSaveResponse> saveFollowers(@AuthenticationPrincipal final String email, @PathVariable Long id) {
        final Follow follow = followService.saveFollower(email, id);
        return ResponseEntity.ok().body(FollowerSaveResponse.ofFollow(follow));
    }

    @PostMapping("/api/v1/follow/followings/{id}")
    public ResponseEntity<FollowingSaveResponse> saveFollowings(@AuthenticationPrincipal final String email, @PathVariable Long id) {
        final Follow follow = followService.saveFollowing(email, id);
        return ResponseEntity.ok().body(FollowingSaveResponse.ofFollow(follow));
    }

}
