package com.ecommerce.domain.user.api;

import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserListResponse>> list() {
        final List<UserListResponse> userListResponses = userService.list();
        return ResponseEntity.ok().body(userListResponses);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable final Long id) {
        final User user = userService.findById(id);
        final UserResponse userResponse = new UserResponse(user);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final User user) {
        final User findUser = userService.findByEmail(user.getEmail(), user.getPassword());
        final LoginResponse loginResponse = new LoginResponse(findUser);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponse> save(@RequestBody final User user) {
        final User savedUser = userService.save(user);
        final UserSaveResponse userSaveResponse = new UserSaveResponse(savedUser);
        return ResponseEntity.ok().body(userSaveResponse);
    }

    @PutMapping("/users")
    public ResponseEntity<UserUpdateResponse> update(@RequestBody final User user) {
        final User updatedUser = userService.update(user);
        final UserUpdateResponse userUpdateResponse = new UserUpdateResponse(updatedUser);
        return ResponseEntity.ok().body(userUpdateResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable final Long id) {
        userService.delete(id);
    }

}
