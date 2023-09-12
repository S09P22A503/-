package com.sshmarket.review.application.port.in;

import com.sshmarket.review.common.SelfValidating;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
public class AddReviewCommand extends SelfValidating<AddReviewCommand> {

    @NotNull
    private final Long articleId;

    @NotNull
    private final Long tradeId;

    @NotNull
    private final Integer startRating;

    @NotNull
    private final String message;

    private final List<MultipartFile> images;

    @Builder
    public AddReviewCommand(Long articleId, Long tradeId, Integer startRating,
            String message, List<MultipartFile> images) {
        this.articleId = articleId;
        this.tradeId = tradeId;
        this.startRating = startRating;
        this.message = message;
        this.images = images;
        this.validateSelf();
    }

}
