package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.out.SaveReviewPort;
import com.sshmarket.review.application.port.out.UploadReviewImagePort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import com.sshmarket.review.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@UseCase
@Transactional
class AddReviewService implements AddReviewUseCase {

    private final SaveReviewPort saveReviewPort;
    private final UploadReviewImagePort insertReviewImagePort;

    @Override
    public boolean addReview(AddReviewCommand addReviewCommand) {

        Review newReview = Review.createReviewWithoutId(addReviewCommand.getMemberId(),
                addReviewCommand.getArticleId(), addReviewCommand.getBuyHistoryId(),
                addReviewCommand.getMessage(), addReviewCommand.getStartRating());

        List<ReviewImage> images = addReviewImages(addReviewCommand.getReviewImages());

        newReview.addReviewImages(images);

        Review savedReview = saveReviewPort.saveReview(newReview);

        if (savedReview != null) {
            return true;
        } else {
            throw new BusinessException("리뷰 생성에 실패했습니다.");
        }

    }

    private List<ReviewImage> addReviewImages(List<MultipartFile> reviewImages) {
        List<ReviewImage> reviewImageDomains = new ArrayList<>();

        for (MultipartFile reviewImage : reviewImages) {
            String fileName = ReviewImage.createFileName(reviewImage);
            String imageUrl = insertReviewImagePort.uploadReviewImage(fileName, reviewImage);
            reviewImageDomains.add(ReviewImage.createReviewImageWithUrl(imageUrl));
        }

        return reviewImageDomains;
    }

}
