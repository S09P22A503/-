package com.sshmarket.review.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ReviewImage {

    private static final String imageDirectory = "review/images/";

    @Getter
    private final Long id;

    @Getter
    private final Long reviewId;

    @Getter
    @NonNull
    private final String imageUrl;

    @Builder
    private ReviewImage(Long id, Long reviewId, @NonNull String imageUrl) {
        this.id = id;
        this.reviewId = reviewId;
        this.imageUrl = imageUrl;
    }

    public static ReviewImage createReviewImageWithUrl(String imageUrl) {
        return ReviewImage.builder()
                          .imageUrl(imageUrl)
                          .build();
    }

    public static ReviewImage createReviewImageWithId(Long id, Long reviewId, String imageUrl) {
        return ReviewImage.builder()
                          .id(id)
                          .reviewId(reviewId)
                          .imageUrl(imageUrl)
                          .build();
    }

    public static String createFileName(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return imageDirectory + UUID.randomUUID() + "." + extension;
    }
}
