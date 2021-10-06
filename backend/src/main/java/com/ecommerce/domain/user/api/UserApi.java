package com.ecommerce.domain.user.api;

import com.ecommerce.domain.user.application.S3Service;
import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.dto.UserSaveRequest;
import com.ecommerce.domain.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final S3Service s3Service;

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
    public ResponseEntity<UserSaveResponse> save(@RequestBody final UserSaveRequest request) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        final User user = userService.save(request.toEntity());
        final UserSaveResponse userSaveResponse = new UserSaveResponse(user);
        return ResponseEntity.ok().body(userSaveResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/v1/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/api/v1/photos")
    public ResponseEntity<String> upload(@AuthenticationPrincipal final String email, @RequestParam("photo") final MultipartFile multipartFile) throws IOException {
        final String fileName = s3Service.uploadFile(email, multipartFile);
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping("/api/v1/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        final byte[] data = s3Service.downloadFile(fileName);
        final ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return ResponseEntity.ok().body(s3Service.deleteFile(fileName));
    }

}
