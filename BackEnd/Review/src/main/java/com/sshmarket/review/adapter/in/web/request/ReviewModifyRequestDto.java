package com.sshmarket.review.adapter.in.web.request;

import com.sshmarket.review.adapter.in.web.request.valid.AllowedContentType;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ReviewModifyRequestDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String message;

    @NotBlank(message = "별점을 입력해주세요.")
    private Integer starRating;

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"})
    private List<MultipartFile> reviewImages;

}
