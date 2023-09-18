package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModifyTradeUseCase {

    private final TradeRepository tradeRepository;
    public void sellTrade(Long id) {
        // Exception 수정 필요
        Trade trade = tradeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 거래가 존재하지 않습니다.")
        );
        trade.sellTrade();
    }

}