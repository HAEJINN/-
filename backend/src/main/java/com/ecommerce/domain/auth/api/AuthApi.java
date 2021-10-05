package com.ecommerce.domain.auth.api;

import com.ecommerce.domain.auth.dto.LoginRequest;
import com.ecommerce.domain.auth.dto.LoginResponse;
import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthApi {

    private final UserService userService;
    private final JwtService jwtProvider;

    @PostMapping("/api/v1/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest request) {
        final User user = userService.login(request.toEntity());
        final String jwtToken = jwtProvider.createToken(user);
        final LoginResponse loginResponse = new LoginResponse(user, jwtToken);
        return ResponseEntity.ok().body(loginResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/api/v1/logout")
    public void logout(final HttpSession httpSession) {
        httpSession.removeAttribute("user");
    }

}
