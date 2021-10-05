package com.ecommerce.domain.photo.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ecommerce.domain.photo.domain.Photo;
import com.ecommerce.domain.photo.domain.PhotoRepository;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PhotoS3Service {

    private final AmazonS3Client amazonS3Client;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String uploadFile(String email, MultipartFile file) {
        final File fileObj = convertMultiPartFileToFile(file);
        final String fileName = fileName(file);

        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        final Photo profileImage = photoRepository.save(new Photo(fileName));
        user.changePhoto(profileImage);

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, fileObj));
        fileObj.delete();
        return fileName;
    }

    private String fileName(final MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonS3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        final File convertedFile = new File(file.getOriginalFilename());    // 전체 경로 + 파일 이름 얻어옴
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {  // 파일 실행
            fos.write(file.getBytes());                                     // 파일 작성
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;                                               // 변환된 파일 반환
    }

}
