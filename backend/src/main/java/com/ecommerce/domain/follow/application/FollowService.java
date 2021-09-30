package com.ecommerce.domain.follow.application;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.follow.domain.FollowRepository;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public long countFollower(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return followRepository.countByFollower(user);
    }

    public long countFollowing(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return followRepository.countByFollowing(user);
    }

    @Transactional
    public Follow following(final String userEmail, final Long followingId) {
        final User user = userRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        final User following = userRepository.findById(followingId).orElseThrow(IllegalArgumentException::new);
        final Follow follow = follower(user, following);
        user.addFollowing(follow);
        return followRepository.save(follow);
    }

    @Transactional
    public Follow follower(final String userEmail, final Long followerId) {
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
