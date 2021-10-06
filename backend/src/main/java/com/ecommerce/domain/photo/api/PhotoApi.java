package com.ecommerce.domain.photo.api;

import com.ecommerce.domain.photo.application.PhotoS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PhotoApi {

    private final PhotoS3Service photoS3Service;

    @PostMapping("/api/v1/photos")
    public ResponseEntity<String> upload(@AuthenticationPrincipal final String email, @RequestParam("photo") final MultipartFile multipartFile) throws IOException {
        final String fileName = photoS3Service.uploadFile(email, multipartFile);
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping("/api/v1/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        final byte[] data = photoS3Service.downloadFile(fileName);
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
        return ResponseEntity.ok().body(photoS3Service.deleteFile(fileName));
    }

}

