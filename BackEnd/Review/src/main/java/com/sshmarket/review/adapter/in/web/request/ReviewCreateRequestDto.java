package com.sshmarket.review.adapter.in.web.request;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReviewCreateRequestDto {

    @NotBlank
    private Long memberId;

    @NotBlank
    private Long articleId;

    @NotBlank
    private Long buyHistoryId;

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String message;

    @NotBlank
    private Integer starRating;

    private List<MultipartFile> images;


}
