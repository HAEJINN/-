package com.ecommerce.domain.follow.api;

import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.follow.application.FollowService;
import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FollowerCount> countFollower(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final long count = followService.countFollower(sessionUser.getEmail());
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

    @GetMapping("/api/v1/follow/followings/count")
    public ResponseEntity<FollowerCount> countFollowing(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final long count = followService.countFollowing(sessionUser.getEmail());
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

    @GetMapping("/api/v1/follow/followers")
    public ResponseEntity<List<FollowerListResponse>> findFollowers(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final List<FollowerListResponse> followers = followService.findFollowers(sessionUser.getEmail());
        return ResponseEntity.ok().body(followers);
    }

    @GetMapping("/api/v1/follow/followings")
    public ResponseEntity<List<FollowingListResponse>> findFollowings(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final List<FollowingListResponse> followings = followService.findFollowings(sessionUser.getEmail());
        return ResponseEntity.ok().body(followings);
    }

    @PostMapping("/api/v1/follow/followers/{id}")
    public ResponseEntity<FollowerSaveResponse> saveFollowers(final HttpSession httpSession, @PathVariable Long id) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final Follow follow = followService.saveFollower(sessionUser.getEmail(), id);
        return ResponseEntity.ok().body(FollowerSaveResponse.ofFollow(follow));
    }

    @PostMapping("/api/v1/follow/followers/{id}")
    public ResponseEntity<FollowingSaveResponse> saveFollowings(final HttpSession httpSession, @PathVariable Long id) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final Follow follow = followService.saveFollowing(sessionUser.getEmail(), id);
        return ResponseEntity.ok().body(FollowingSaveResponse.ofFollow(follow));
    }

}
