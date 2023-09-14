package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.out.InsertReviewImagePort;
import com.sshmarket.review.application.port.out.InsertReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@UseCase
@Transactional
class AddReviewService implements AddReviewUseCase {

    private static final String imageDirectory = "review/images/";

    private final InsertReviewPort insertReviewPort;
    private final InsertReviewImagePort insertReviewImagePort;

    @Override
    public boolean addReview(AddReviewCommand addReviewCommand) {

        Review newReview = Review.createReviewWithoutId(addReviewCommand.getMemberId(),
                addReviewCommand.getArticleId(), addReviewCommand.getBuyHistoryId(),
                addReviewCommand.getMessage(), addReviewCommand.getStartRating());

        Long newId = insertReviewPort.insertReview(newReview);

        return false;
    }

    private List<ReviewImage> createReviewImages(Long reviewId, List<MultipartFile> reviewImages) {
        List<ReviewImage> images = new ArrayList<>();

        for (MultipartFile reviewImage : reviewImages) {
            String fileName = createFileName(reviewImage);
            String imageUrl = insertReviewImagePort.insertImage(fileName, reviewImage);
            images.add(ReviewImage.createReviewImageWithoutId(reviewId, imageUrl));
        }

        return images;
    }

    private String createFileName(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return imageDirectory + UUID.randomUUID() + "." + extension;
    }
}
