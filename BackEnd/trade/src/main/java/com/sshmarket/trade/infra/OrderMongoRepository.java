package com.sshmarket.trade.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderCurrent, String> {
}
