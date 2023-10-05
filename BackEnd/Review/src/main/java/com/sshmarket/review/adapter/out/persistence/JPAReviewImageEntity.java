package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.domain.ReviewImage;
import java.util.List;
import javax.persistence.CascadeType;
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
@AllArgsConstructor
@Builder
class JPAReviewImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", nullable = false)
    private JPAReviewEntity review;

    @Column(nullable = false)
    private String imageUrl;


    protected static JPAReviewImageEntity from(ReviewImage reviewImage, JPAReviewEntity review) {
        return JPAReviewImageEntity.builder()
                                   .imageUrl(reviewImage.getImageUrl())
                                   .review(review)
                                   .build();
    }

    protected ReviewImage convertToDomain() {
        return ReviewImage.builder()
                          .id(this.id)
                          .reviewId(this.review.getId())
                          .imageUrl(this.imageUrl)
                          .build();
    }

}
