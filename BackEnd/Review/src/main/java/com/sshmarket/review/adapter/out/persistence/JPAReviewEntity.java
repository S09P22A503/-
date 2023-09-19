package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.adapter.out.persistence.reviewImage.JPAReviewImageEntity;
import com.sshmarket.review.domain.Review;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @Column
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "review")
    private List<JPAReviewImageEntity> images = new ArrayList<>();

    protected static JPAReviewEntity from(Review review) {
        return null;
    }

    protected Review convertToDomain() {
        return null;
    }
}
