package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModifyTradeUseCase {

    private final TradeRepository tradeRepository;
    public void sellTrade(Long tradeId) {

    }

}
