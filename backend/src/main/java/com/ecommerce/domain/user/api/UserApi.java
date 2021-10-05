package com.ecommerce.domain.user.api;

import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.dto.UserSaveRequest;
import com.ecommerce.domain.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApi {

    private final UserService userService;

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
    public ResponseEntity<UserSaveResponse> save(HttpSession httpSession, @RequestBody final UserSaveRequest request) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        final User user = userService.save(request.toEntity());
        System.out.println(httpSession.getId());
        final UserSaveResponse userSaveResponse = new UserSaveResponse(user);
        return ResponseEntity.ok().body(userSaveResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/v1/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
