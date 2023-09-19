package com.sshmarket.review.adapter.in.web.request;

import com.sshmarket.review.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.review.application.port.in.AddReviewCommand;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReviewAddRequestDto {

    @NotBlank
    private Long memberId;

    @NotBlank
    private Long articleId;

    @NotBlank
    private Long buyHistoryId;

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String message;

    @NotBlank(message = "별점을 입력해주세요.")
    private Integer starRating;

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"})
    private List<MultipartFile> reviewImages;

    public AddReviewCommand covertToCommand() {
        return AddReviewCommand.builder()
                               .memberId(memberId)
                               .articleId(articleId)
                               .buyHistoryId(buyHistoryId)
                               .message(message)
                               .startRating(starRating)
                               .reviewImages(reviewImages)
                               .build();
    }

}
