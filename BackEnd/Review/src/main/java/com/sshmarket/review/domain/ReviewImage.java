package com.sshmarket.review.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewImage {

    private static final String imageDirectory = "review/images/";

    @Getter
    private final Long id;

    @Getter
    @NonNull
    private final Long reviewId;

    @Getter
    @NonNull
    private final String imageUrl;

    public static ReviewImage createReviewImageWithoutId(Long reviewId, String imageUrl) {
        return new ReviewImage(null, reviewId, imageUrl);
    }

    public static ReviewImage createReviewImageWithId(Long id, Long reviewId, String imageUrl) {
        return new ReviewImage(id, reviewId, imageUrl);
    }

    public static String createFileName(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return imageDirectory + UUID.randomUUID() + "." + extension;
    }
}
