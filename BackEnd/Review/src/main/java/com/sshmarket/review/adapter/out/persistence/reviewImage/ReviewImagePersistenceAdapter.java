package com.sshmarket.review.adapter.out.persistence.reviewImage;

import com.sshmarket.review.adapter.out.persistence.S3Uploader;
import com.sshmarket.review.application.port.out.InsertReviewImagePort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@PersistenceAdapter
@AllArgsConstructor
public class ReviewImagePersistenceAdapter implements InsertReviewImagePort {

    private final S3Uploader s3Uploader;
    private static String IMAGE_DIRECTORY = "https://chaekbang-bucket.s3.ap-northeast-2.amazonaws.com/";

    @Override
    public String insertImage(String fileName, MultipartFile image) {
        return null;
    }
}
