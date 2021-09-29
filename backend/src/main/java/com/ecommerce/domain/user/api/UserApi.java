package com.ecommerce.domain.user.api;

import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.dto.UserSaveRequest;
import com.ecommerce.domain.user.dto.UserSaveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApi {

    private final UserService userService;

    public UserApi(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        final User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAll() {
        final List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<UserSaveResponse> save(@RequestBody final UserSaveRequest request) {
        final User user = userService.save(request.toEntity());
        final UserSaveResponse userSaveResponse = new UserSaveResponse(user);
        return ResponseEntity.ok().body(userSaveResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/v1/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}