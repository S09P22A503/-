package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.out.InsertReviewImagePort;
import com.sshmarket.review.application.port.out.InsertReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import com.sshmarket.review.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@UseCase
@Transactional
class AddReviewService implements AddReviewUseCase {

    private final InsertReviewPort insertReviewPort;
    private final InsertReviewImagePort insertReviewImagePort;

    @Override
    public boolean addReview(AddReviewCommand addReviewCommand) {

        Review newReview = Review.createReviewWithoutId(addReviewCommand.getMemberId(),
                addReviewCommand.getArticleId(), addReviewCommand.getBuyHistoryId(),
                addReviewCommand.getMessage(), addReviewCommand.getStartRating());

        Review savedReview = insertReviewPort.insertReview(newReview);
        List<ReviewImage> images = createReviewImages(savedReview.getId(),
                addReviewCommand.getReviewImages());
        savedReview.addReviewImages(images);

        if (savedReview != null) {
            return true;
        } else {
            throw new BusinessException(HttpStatus.NOT_ACCEPTABLE.value(), "리뷰 생성에 실패했습니다.");
        }
    }

    private List<ReviewImage> createReviewImages(Long reviewId, List<MultipartFile> reviewImages) {
        List<ReviewImage> images = new ArrayList<>();

        for (MultipartFile reviewImage : reviewImages) {
            String fileName = ReviewImage.createFileName(reviewImage);
            String imageUrl = insertReviewImagePort.insertImage(fileName, reviewImage);
            images.add(ReviewImage.createReviewImageWithoutId(reviewId, imageUrl));
        }

        return images;
    }

}
