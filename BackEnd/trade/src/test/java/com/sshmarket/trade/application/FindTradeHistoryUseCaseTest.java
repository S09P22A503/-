package com.sshmarket.trade.application;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeHistory;
import com.sshmarket.trade.dto.TradeHistoryResponseDto;
import com.sshmarket.trade.infra.database.JpaTradeHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class FindTradeHistoryUseCaseTest {

    @Autowired
    private FindTradeHistoryUseCase findTradeHistoryUseCase;
    @Autowired
    private JpaTradeHistoryRepository jpaTradeHistoryRepository;

    @Test
    void 거래_내역_조회() {
        Trade trade = Trade.createTrade(LocalDateTime.now(), Status.CHAT, 10L, 10L, 20L, 0L, 0L);
        TradeHistory tradeHistory = TradeHistory.createTradeHistory(trade, false, 1000, "image", "감자팔아요");
        jpaTradeHistoryRepository.save(tradeHistory);
        Pageable pageable = PageRequest.of(0, 10);

        Page<TradeHistoryResponseDto> tradeHistorys = findTradeHistoryUseCase.findTradeHistory(10L, pageable);
        assertThat(tradeHistorys.getContent().get(0).getTradeId()).isEqualTo(tradeHistory.getId());
    }

}