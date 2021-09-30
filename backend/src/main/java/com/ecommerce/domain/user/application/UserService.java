package com.ecommerce.domain.user.application;

import com.ecommerce.domain.photo.domain.Photo;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        final Photo photo = new Photo(UUID.randomUUID().toString());
        user.changePhoto(photo);
        user.passwordEncode(passwordEncoder);
        return userRepository.save(user);
    }

    @Transactional
    public void update(Long id, String photo) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.changeEmail(photo);
    }

    @Transactional
    public void delete(Long id) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User login(final User loginUser) {
        final User user = userRepository.findByEmail(loginUser.getEmail()).orElseThrow();
        user.matchPassword(passwordEncoder, loginUser.getPassword());
        return user;
    }

}
