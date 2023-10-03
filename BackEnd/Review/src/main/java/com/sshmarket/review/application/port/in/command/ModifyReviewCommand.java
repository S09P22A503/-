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
public class ModifyReviewCommand extends SelfValidating<ModifyReviewCommand> {

    @NotBlank
    private final Long id;

    @NotBlank
    private final Integer startRating;

    @NotBlank
    private final String message;

    private final List<Long> savedReviewIds;

    private final List<MultipartFile> newReviewImages;

    @Builder
    public ModifyReviewCommand(Long id, Integer startRating, String message,
            List<Long> savedReviewIds, List<MultipartFile> newReviewImages) {
        this.id = id;
        this.startRating = startRating;
        this.message = message;
        this.savedReviewIds = savedReviewIds;
        this.newReviewImages = newReviewImages;
        this.validateSelf();
    }

}
