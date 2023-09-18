package com.sshmarket.trade.infra.database;

import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTradeRepository extends JpaRepository<Trade, Long> {

    Trade findByArticleIdAndBuyerIdAndStatusNotIn(Long articleId, Long buyerId, List<Status> status);
}

