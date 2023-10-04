package com.sshmarket.review.application.port.in.command;

import com.sshmarket.review.common.SelfValidating;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;


@Value
@EqualsAndHashCode(callSuper = false)
public class AddReviewCommand {

    private final Long memberId;

    private final Long articleId;

    private final Long buyHistoryId;

    private final Integer starRating;

    private final String message;

    private final List<MultipartFile> reviewImages;

    @Builder
    public AddReviewCommand(Long memberId, Long articleId,
            Long buyHistoryId, Integer starRating, String message,
            List<MultipartFile> reviewImages) {
        this.memberId = memberId;
        this.articleId = articleId;
        this.buyHistoryId = buyHistoryId;
        this.starRating = starRating;
        this.message = message;
        this.reviewImages = reviewImages;
    }

}
