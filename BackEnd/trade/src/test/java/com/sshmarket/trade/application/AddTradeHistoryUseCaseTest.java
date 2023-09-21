package com.sshmarket.trade.application;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeHistory;
import com.sshmarket.trade.dto.TradeHistoryCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AddTradeHistoryUseCaseTest {

    @Autowired
    private AddTradeHistoryUseCase addTradeHistoryUseCase;

    @Test
    void 거래_생성() {
        Trade trade = Trade.createTrade(LocalDateTime.now(), Status.CHAT, 10L, 10L, 20L, 0L, 0L);

        TradeHistory tradeHistory = addTradeHistoryUseCase.addTradeHistory(trade, new TradeHistoryCreateRequestDto(1000, "image", "감자팔아요"));
        System.out.println(tradeHistory);
        assertNotNull(tradeHistory);
    }

}