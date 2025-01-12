package com.ecommerce.domain.user.api;

import com.ecommerce.domain.user.application.S3Service;
import com.ecommerce.domain.user.application.UserService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.dto.UserFindListResponse;
import com.ecommerce.domain.user.dto.UserFindResponse;
import com.ecommerce.domain.user.dto.UserSaveRequest;
import com.ecommerce.domain.user.dto.UserSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RequiredArgsConstructor
@RestController
public class UserApi {

    private final UserService userService;
    private final S3Service s3Service;

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        final User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserFindResponse(user));
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAll() {
        final List<UserFindListResponse> userFindListResponses = userService.findAll();
        return ResponseEntity.ok().body(userFindListResponses);
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

    @PostMapping("/api/v1/users/upload")
    public ResponseEntity<?> update(@AuthenticationPrincipal final String email,
                                         @RequestParam final String description,
                                         @RequestParam final MultipartFile photo) throws IOException {
        s3Service.uploadFile(email, description, photo);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("api/v1/users/latest")
    public ResponseEntity<List<UserFindListResponse>> findAllLatest(@PageableDefault(
            page = 0, size = 4, sort = "createdAt", direction = ASC) final Pageable pageable) {
        final List<UserFindListResponse> latest = userService.findAllLatest(pageable);
        return ResponseEntity.ok().body(latest);
    }

    @GetMapping("api/v1/users/random")
    public ResponseEntity<List<UserFindListResponse>> findAllRandom() {
        return ResponseEntity.ok().body(userService.findAllRandom());
    }

//    프로필 사진 취소시 말씀해주세요
//    @GetMapping("/api/v1/download/{fileName}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
//        final byte[] data = s3Service.downloadFile(fileName);
//        final ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
//    }
//
//    @DeleteMapping("/delete/{fileName}")
//    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
//        return ResponseEntity.ok().body(s3Service.deleteFile(fileName));
//    }

}
