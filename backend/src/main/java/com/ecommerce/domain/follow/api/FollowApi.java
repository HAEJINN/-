package com.ecommerce.domain.follow.api;

import com.ecommerce.domain.auth.domain.SessionUser;
import com.ecommerce.domain.follow.application.FollowService;
import com.ecommerce.domain.follow.dto.FollowerCount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FollowApi {

    private final FollowService followService;

    @GetMapping("/api/v1/follow/follower/count")
    public ResponseEntity<FollowerCount> countFollower(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final long count = followService.countFollower(sessionUser.getEmail());
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

    @GetMapping("/api/v1/follow/following/count")
    public ResponseEntity<FollowerCount> countFollowing(final HttpSession httpSession) {
        final SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        final long count = followService.countFollowing(sessionUser.getEmail());
        final FollowerCount followerCount = new FollowerCount(count);
        return ResponseEntity.ok().body(followerCount);
    }

}
