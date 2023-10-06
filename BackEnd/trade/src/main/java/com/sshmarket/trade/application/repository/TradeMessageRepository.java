package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.TradeMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TradeMessageRepository extends MongoRepository<TradeMessage, String> {

    List<TradeMessage> findByTradeId(Long tradeId);

    TradeMessage findTopByTradeIdOrderByCreatedAtDesc(Long tradeId);

    @Query("{ 'message': { '$regex': ?0 }, 'tradeId': { '$in': ?1 } }")
    List<TradeMessage> findByMessageContainingAndTradeIdIn(String message, List<Long> tradeIds);

}
