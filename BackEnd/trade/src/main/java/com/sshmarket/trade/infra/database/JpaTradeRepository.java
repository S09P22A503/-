package com.sshmarket.trade.infra.database;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaTradeRepository extends JpaRepository<Trade, Long> {

    Trade findByArticleIdAndBuyerIdAndStatusNotIn(Long articleId, Long buyerId,
            List<Status> status);

    @Query(value = "SELECT * FROM trade "
            + "WHERE seller_id = :memberId "
            + "OR buyer_id = :memberId "
            + "AND status = :status", nativeQuery = true)
    List<Trade> findByMemberIdAndStatus(@Param("memberId") Long memberId,
            @Param("status") String status);

    @Query(value = "SELECT * FROM trade "
            + "WHERE seller_id = :memberId "
            + "OR buyer_id = :memberId ", nativeQuery = true)
    List<Trade> findByMemberId(@Param("memberId") Long memberId);
}

