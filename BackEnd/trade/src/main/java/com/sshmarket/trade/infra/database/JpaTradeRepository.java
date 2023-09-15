package com.sshmarket.trade.infra.database;

import com.sshmarket.trade.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTradeRepository extends JpaRepository<Trade, Long> {

}
