package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import java.util.List;
import java.util.Optional;

public interface TradeRepository {

    Trade save(Trade trade);

    Trade findByArticleIdAndBuyerIdAndStatusNotIn(Long articleId, Long buyerId, List<Status> status);

    Optional<Trade> findById(Long id);

    List<Trade> findByMemberIdAndStatus(Long memberId, Status status);
}

