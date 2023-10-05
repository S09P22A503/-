package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.command.AddReviewCommand;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.out.SaveReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import com.sshmarket.review.exception.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class AddReviewService implements AddReviewUseCase {

    private final SaveReviewPort saveReviewPort;
    private final UploadReviewImageService uploadReviewImageService;

    @Override
    public void addReview(AddReviewCommand addReviewCommand) {

        Review newReview = Review.createReviewWithoutId(addReviewCommand.getMemberId(),
                addReviewCommand.getArticleId(), addReviewCommand.getBuyHistoryId(),
                addReviewCommand.getMessage(), addReviewCommand.getStarRating());

        List<ReviewImage> images = uploadReviewImageService.addReviewImages(
                addReviewCommand.getReviewImages());

        newReview.addReviewImages(images);

        Review savedReview = saveReviewPort.saveReview(newReview);

        if (savedReview == null) {
            throw new BusinessException("리뷰 생성에 실패했습니다.");
        }

    }

}
