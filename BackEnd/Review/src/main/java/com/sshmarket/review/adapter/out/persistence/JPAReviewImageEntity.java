package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.domain.ReviewImage;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_image")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
class JPAReviewImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private JPAReviewEntity jpaReviewEntity;

    @Column(nullable = false)
    private String imageUrl;

    protected static JPAReviewImageEntity from(ReviewImage reviewImage) {
        return JPAReviewImageEntity.builder()
                                   .imageUrl(reviewImage.getImageUrl())
                                   .build();
    }

    protected ReviewImage convertToDomain() {
        return ReviewImage.builder()
                          .id(this.id)
                          .reviewId(this.jpaReviewEntity.getId())
                          .imageUrl(this.imageUrl)
                          .build();
    }

}
