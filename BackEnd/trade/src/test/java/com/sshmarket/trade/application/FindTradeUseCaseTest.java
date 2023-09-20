package com.sshmarket.trade.application;

import static org.assertj.core.api.Assertions.*;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.dto.TradeCreateRequestDto;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class FindTradeUseCaseTest {

    @Autowired
    private FindTradeUseCase findTradeUseCase;
    @Autowired
    private AddTradeUseCase tradeUseCase;

    @Test
    void 채팅방_조회() {
        Trade trade = tradeUseCase.addTrade(new TradeCreateRequestDto(1L, 10L, 20L));
        Trade trade2 = tradeUseCase.addTrade(new TradeCreateRequestDto(2L, 30L, 10L));
        Trade trade3 = tradeUseCase.addTrade(new TradeCreateRequestDto(3L, 40L, 50L));
        List<Trade> trades = findTradeUseCase.findTrades(10L, Status.CHAT);
        assertThat(trades).hasSize(2);
    }

}