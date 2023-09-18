package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.TradeMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeMessageRepository extends MongoRepository<TradeMessage, String> {
}
