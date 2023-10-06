package com.sshmarket.review.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface JPAReviewRepository extends JpaRepository<JPAReviewEntity, Long> {

    List<JPAReviewEntity> findAllByMemberId(Long memberId);

    List<JPAReviewEntity> findAllByArticleId(Long articleId);

}
