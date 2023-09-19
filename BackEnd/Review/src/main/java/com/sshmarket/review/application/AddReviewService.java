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

        List<ReviewImage> images = addReviewImages(newReview.getId(),
                addReviewCommand.getReviewImages());

        newReview.addReviewImages(images);
        
        Review savedReview = insertReviewPort.insertReview(newReview);

        if (savedReview != null) {
            return true;
        } else {
            throw new BusinessException("리뷰 생성에 실패했습니다.");
        }

    }

    private List<ReviewImage> addReviewImages(Long reviewId, List<MultipartFile> reviewImages) {
        List<ReviewImage> images = new ArrayList<>();

        for (MultipartFile reviewImage : reviewImages) {
            String fileName = ReviewImage.createFileName(reviewImage);
            String imageUrl = insertReviewImagePort.uploadReviewImage(fileName, reviewImage);
            images.add(ReviewImage.createReviewImageWithUrl(imageUrl));
        }

        return images;
    }

}
