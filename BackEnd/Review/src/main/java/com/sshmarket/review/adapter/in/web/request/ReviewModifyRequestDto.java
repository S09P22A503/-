package com.sshmarket.review.adapter.in.web.request;

import com.sshmarket.review.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.review.application.port.in.command.AddReviewCommand;
import com.sshmarket.review.application.port.in.command.ModifyReviewCommand;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReviewModifyRequestDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String message;

    @NotBlank(message = "별점을 입력해주세요.")
    private Integer starRating;

    private List<Long> savedReviewIds;

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"})
    private List<MultipartFile> newReviewImages;

    public ModifyReviewCommand covertToCommand() {
        return ModifyReviewCommand.builder()
                                  .id(id)
                                  .message(message)
                                  .startRating(starRating)
                                  .savedReviewIds(savedReviewIds)
                                  .newReviewImages(newReviewImages)
                                  .build();
    }
}
