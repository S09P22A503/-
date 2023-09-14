package com.sshmarket.review.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface InsertReviewImagePort {
    String insertImage(String fileName, MultipartFile image);
}
