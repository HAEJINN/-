package com.ecommerce.domain;

import com.ecommerce.domain.auth.LoginUser;
import com.ecommerce.domain.auth.session.SessionUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<String> test(@LoginUser SessionUser user) {
        return ResponseEntity.ok().body(user.toString());
    }
}
