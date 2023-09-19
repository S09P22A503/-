package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.adapter.out.persistence.S3Uploader;
import com.sshmarket.review.application.port.out.InsertReviewImagePort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@PersistenceAdapter
@AllArgsConstructor
public class ReviewImagePersistenceAdapter implements InsertReviewImagePort {

    private final S3Uploader s3Uploader;
    private final JPAReviewImageRepository jpaReviewImageRepository;

    private static final String IMAGE_DIRECTORY = "https://chaekbang-bucket.s3.ap-northeast-2.amazonaws.com/";

    @Override
    public String uploadReviewImage(String fileName, MultipartFile image) {
        s3Uploader.upload(fileName, image);
        return IMAGE_DIRECTORY + fileName;
    }
}
