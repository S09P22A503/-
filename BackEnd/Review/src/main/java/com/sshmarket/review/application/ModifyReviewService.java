package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.ModifyReviewUseCase;
import com.sshmarket.review.application.port.in.command.ModifyReviewCommand;
import com.sshmarket.review.application.port.out.UpdateReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyReviewService implements ModifyReviewUseCase {

    private final UpdateReviewPort updateReviewPort;
    private final UploadReviewImageService uploadReviewImageService;


    @Override
    public void modifyReview(ModifyReviewCommand modifyReviewCommand) {
        Review modifyReview = Review.builder()
                                    .id(modifyReviewCommand.getId())
                                    .starRating(modifyReviewCommand.getStartRating())
                                    .message(modifyReviewCommand.getMessage())
                                    .reviewImages(new ArrayList<>())
                                    .build();

        List<ReviewImage> images = uploadReviewImageService.addReviewImages(
                modifyReviewCommand.getNewReviewImages());

        modifyReview.addReviewImages(images);

        updateReviewPort.updateReview(modifyReview, modifyReviewCommand.getSavedReviewIds());
    }


}
