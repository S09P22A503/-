package com.sshmarket.review.application;

import com.sshmarket.review.application.port.out.UploadReviewImagePort;
import com.sshmarket.review.domain.ReviewImage;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class AddReviewImageService {

    private final UploadReviewImagePort insertReviewImagePort;

    public List<ReviewImage> addReviewImages(List<MultipartFile> reviewImages) {

        List<ReviewImage> reviewImageDomains = new ArrayList<>();

        for (MultipartFile reviewImage : reviewImages) {
            String fileName = ReviewImage.createFileName(reviewImage);
            String imageUrl = insertReviewImagePort.uploadReviewImage(fileName, reviewImage);
            reviewImageDomains.add(ReviewImage.createReviewImageWithUrl(imageUrl));
        }

        return reviewImageDomains;
    }
}
