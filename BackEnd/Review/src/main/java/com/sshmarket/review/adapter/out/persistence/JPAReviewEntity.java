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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class JPAReviewEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "review")
    private List<JPAReviewImageEntity> images = new ArrayList<>();

    @Builder
    private JPAReviewEntity(Long id, Long memberId, Long articleId, Long buyHistoryId,
            String message, int starRating, LocalDateTime createdAt,
            List<JPAReviewImageEntity> images) {
        this.id = id;
        this.memberId = memberId;
        this.articleId = articleId;
        this.buyHistoryId = buyHistoryId;
        this.message = message;
        this.starRating = starRating;
        this.createdAt = createdAt;
        this.images = images;
    }

    protected static JPAReviewEntity from(Review review) {
        return JPAReviewEntity.builder()
                              .articleId(review.getId())
                              .memberId(review.getMemberId())
                              .buyHistoryId(review.getBuyHistoryId())
                              .message(review.getMessage())
                              .starRating(review.getStarRating())
                              .build();
    }

    protected Review convertToDomain() {
        return Review.createReviewWithId(this.id, this.memberId, this.articleId,
                this.buyHistoryId,
                this.message, this.starRating, this.images.stream()
                                                          .map((JPAReviewImageEntity) -> ReviewImage.createReviewImageWithId(
                                                                  JPAReviewImageEntity.getId(),
                                                                  this.id,
                                                                  JPAReviewImageEntity.getImageUrl()))
                                                          .collect(
                                                                  Collectors.toList()));
    }

    protected void addReviewImages(List<JPAReviewImageEntity> images) {
        this.images.addAll(images);
    }
}
