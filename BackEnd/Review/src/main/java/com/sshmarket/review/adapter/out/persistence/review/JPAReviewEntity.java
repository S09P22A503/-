package com.sshmarket.review.adapter.out.persistence.review;

import com.sshmarket.review.domain.Review;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class JPAReviewEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected static JPAReviewEntity from(Review review) {
        return null;
    }

    protected Review convertToDomain() {
        return null;
    }
}
