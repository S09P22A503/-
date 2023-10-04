package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.adapter.out.persistence.JPAReviewImageEntity;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class JPAReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Long buyHistoryId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private int starRating;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "review")
    private List<JPAReviewImageEntity> reviewImages = new ArrayList<>();

    @Builder
    private JPAReviewEntity(Long articleId, Long memberId, Long buyHistoryId, String message,
            int starRating, List<JPAReviewImageEntity> reviewImages) {
        this.articleId = articleId;
        this.memberId = memberId;
        this.buyHistoryId = buyHistoryId;
        this.message = message;
        this.starRating = starRating;
        this.reviewImages = reviewImages;
    }

    protected static JPAReviewEntity from(Review review) {
        return JPAReviewEntity.builder()
                              .articleId(review.getArticleId())
                              .memberId(review.getMemberId())
                              .buyHistoryId(review.getBuyHistoryId())
                              .message(review.getMessage())
                              .starRating(review.getStarRating())
                              .reviewImages(new ArrayList<>())
                              .build();
    }

    protected Review convertToDomain() {
        return Review.createReviewWithId(this.id, this.memberId, this.articleId,
                this.buyHistoryId,
                this.message, this.starRating, this.reviewImages.stream()
                                                                .map(JPAReviewImageEntity::convertToDomain)
                                                                .collect(
                                                                        Collectors.toList()));
    }

    protected void addReviewImages(List<JPAReviewImageEntity> reviewImages) {
        this.reviewImages.addAll(reviewImages);
    }
}
