package com.ecommerce.domain.follow.application;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.domain.FollowRepository;
import com.ecommerce.domain.follow.dto.FollowerListResponse;
import com.ecommerce.domain.follow.dto.FollowingListResponse;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    // 내가 기준이여서 내가 following 인 경우의 수를 센다.
    public long countFollower(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return followRepository.countByFollowing(user);
    }

    // 내가 기준이여서 내가 follower 인 경우의 수를 센다.
    public long countFollowing(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return followRepository.countByFollower(user);
    }

    // 자신이 팔로잉이어야지 팔로우 찾아서 역으로 검색함
    public List<FollowerListResponse> findMyFollowers(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        final List<Follow> myFollowers = followRepository.findByFollowing(user);
        System.out.println("찾아옴");
        return myFollowers.stream()
                .map(FollowerListResponse::ofFollow)
                .collect(Collectors.toList());
    }

    // 자신이 팔로잉이어야지 팔로우 찾아서 역으로 검색함
    public List<FollowingListResponse> findMyFollowings(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        final List<Follow> myFollowers = followRepository.findByFollower(user);
        System.out.println("찾아옴");
        return myFollowers.stream()
                .map(FollowingListResponse::ofFollow)
                .collect(Collectors.toList());
    }

    public List<FollowerListResponse> findFollowers(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        final List<Follow> followings = followRepository.findByFollowing(user);
        return followings.stream()
                .map(FollowerListResponse::ofFollow)
                .collect(Collectors.toList());
    }

    public List<FollowingListResponse> findFollowings(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        final List<Follow> followings = followRepository.findByFollower(user);
        return followings.stream()
                .map(FollowingListResponse::ofFollow)
                .collect(Collectors.toList());
    }

    @Transactional
    public Follow saveFollowing(final String userEmail, final Long followingId) {
        final User user = userRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        final User following = userRepository.findById(followingId).orElseThrow(IllegalArgumentException::new);
        final Follow follow = follower(user, following);
        user.addFollowing(follow);
        return followRepository.save(follow);
    }

    @Transactional
    public Follow saveFollower(final String userEmail, final Long followerId) {
        final User user = userRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        final User follower = userRepository.findById(followerId).orElseThrow(IllegalArgumentException::new);
        final Follow follow = follower(follower, user);
        user.addFollower(follow);
        return followRepository.save(follow);
    }

    private static Follow follower(final User user, final User following) {
        return Follow.builder()
                .follower(user)
                .following(following)
                .build();
    }


}
