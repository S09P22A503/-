package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.TradeMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeMessageRepository extends MongoRepository<TradeMessage, String> {

    List<TradeMessage> findByTradeId(Long tradeId);

    TradeMessage findTopByTradeIdOrderByCreatedAtDesc(Long tradeId);
}
