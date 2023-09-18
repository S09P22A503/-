package com.sshmarket.trade.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.sshmarket.trade.domain.TradeMessage;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class TradeMessageRepositoryTest {

    @Autowired
    private TradeMessageRepository tradeMessageRepository;

    @Test
    void 메시지가_저장된다() {
        // given
        TradeMessage tradeMessage = TradeMessage.createTradeMessage("메시지1", 1L, 2L, LocalDateTime.now());
        // when
        TradeMessage saved = tradeMessageRepository.save(tradeMessage);
        // then
        assertThat(saved).isNotNull();
    }
}