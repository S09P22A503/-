package com.sshmarket.trade.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sshmarket.trade.dto.TradeCreateRequestDto;
import com.sshmarket.trade.domain.Trade;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class TradeUseCaseTest {

    @Autowired
    private AddTradeUseCase tradeUseCase;

    @Test
    void 채팅방이_생성된다() {
        Trade trade = tradeUseCase.addTrade(new TradeCreateRequestDto(1L, 2L, 3L));
        assertNotNull(trade);
    }

    @Test
    void 채팅방_존재시_새로_생성되지_않는다() {
        Trade trade = tradeUseCase.addTrade(new TradeCreateRequestDto(1L, 2L, 3L));
        Trade trade2 = tradeUseCase.addTrade(new TradeCreateRequestDto(1L, 2L, 3L));
        assertThat(trade).isEqualTo(trade2);
    }

}