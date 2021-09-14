package com.ecommerce.domain.user.application;

import com.ecommerce.domain.exception.DomainException;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.exception.ApplicationException;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.user.dto.UserListResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserListResponse> list() {
        return userRepository.findAll().stream()
                .map(UserListResponse::new)
                .collect(Collectors.toList());
    }

    public User findById(final Long id) {
        return findUserById(id);
    }

    public User findByEmail(final String email, final String password) {
        final User user = findUserByEmail(email);
        user.matchPassword(password);
        return user;
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public User update(final User user) {
        final User findUser = findUserByEmail(user.getEmail());
        return findUser.update(user);
    }

    public void delete(final Long id) {
        final User user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    private User findUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

}
