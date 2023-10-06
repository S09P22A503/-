package com.sshmarket.review.adapter.out.persistence;

import java.util.List;
import jdk.swing.interop.LightweightContentWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

interface JPAReviewImageRepository extends JpaRepository<JPAReviewImageEntity, Long> {

    List<JPAReviewImageEntity> findAllByReview(JPAReviewEntity jpaReviewEntity);

}
