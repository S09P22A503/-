package com.sshmarket.review.application.port.in;

import com.sshmarket.review.common.SelfValidating;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;


@Value
@EqualsAndHashCode(callSuper = false)
public class AddReviewCommand extends SelfValidating<AddReviewCommand> {
    
    @NotBlank
    private final Long memberId;

    @NotBlank
    private final Long articleId;

    @NotBlank
    private final Long buyHistoryId;

    @NotBlank
    private final Integer startRating;

    @NotBlank
    private final String message;

    private final List<MultipartFile> reviewImages;

    @Builder
    public AddReviewCommand(Long memberId, Long articleId,
            Long buyHistoryId, Integer startRating, String message,
            List<MultipartFile> reviewImages) {
        this.memberId = memberId;
        this.articleId = articleId;
        this.buyHistoryId = buyHistoryId;
        this.startRating = startRating;
        this.message = message;
        this.reviewImages = reviewImages;
        this.validateSelf();
    }

}
