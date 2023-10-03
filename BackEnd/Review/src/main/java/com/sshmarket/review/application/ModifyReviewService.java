package com.sshmarket.review.application;

import com.sshmarket.review.adapter.in.web.request.ReviewModifyRequestDto;
import com.sshmarket.review.application.port.in.ModifyReviewUseCase;
import com.sshmarket.review.application.port.in.command.ModifyReviewCommand;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.application.port.out.UpdateReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyReviewService implements ModifyReviewUseCase {

    private final LoadReviewPort loadReviewPort;
    private final UpdateReviewPort updateReviewPort;
    private final AddReviewImageService addReviewImageService;

    @Override
    public void modifyReview(ModifyReviewCommand modifyReviewCommand) {
        Review oldReview = loadReviewPort.findReviewById(modifyReviewCommand.getId());

        // 새로운 리뷰 이미지 저장
        List<ReviewImage> newReviewImages = addReviewImageService.addReviewImages(
                modifyReviewCommand.getNewReviewImages());

        oldReview.modifyReview(modifyReviewCommand.getMessage(),
                modifyReviewCommand.getStartRating(), modifyReviewCommand.getSavedReviewIds(),
                newReviewImages);

        updateReviewPort.updateReview(oldReview);


    }


}
