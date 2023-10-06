package com.sshmarket.review.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadReviewImagePort {
    String uploadReviewImage(String fileName, MultipartFile reviewImage);
}
