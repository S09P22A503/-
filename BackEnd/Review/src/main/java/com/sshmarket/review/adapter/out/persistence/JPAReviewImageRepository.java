package com.sshmarket.review.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface JPAReviewImageRepository extends JpaRepository<JPAReviewImageEntity, Long> {

    List<JPAReviewImageEntity> findAllByReview(JPAReviewEntity jpaReviewEntity);
}
