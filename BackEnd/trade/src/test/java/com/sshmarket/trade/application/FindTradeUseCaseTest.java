package com.sshmarket.trade.application;

import static org.assertj.core.api.Assertions.*;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MessageDto;
import com.sshmarket.trade.dto.TradeCreateRequestDto;
import java.util.List;
import javax.transaction.Transactional;

import com.sshmarket.trade.dto.TradeResponseDto;
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
    @Autowired
    private SendMessageUseCase sendMessageUseCase;

    @Test
    void 채팅방_조회() {

        Trade trade = tradeUseCase.addTrade(new TradeCreateRequestDto(1L, 10L, 20L));
        TradeMessage message = sendMessageUseCase.sendMessage(new MessageDto("안녕", trade.getId(), trade.getBuyerId()));

        Trade trade2 = tradeUseCase.addTrade(new TradeCreateRequestDto(3L, 40L, 10L));
        TradeMessage lastMessage= sendMessageUseCase.sendMessage(new MessageDto("마지막 메시지", trade2.getId(), trade2.getBuyerId()));

        Trade trade3 = tradeUseCase.addTrade(new TradeCreateRequestDto(2L, 10L, 30L));

        List<TradeResponseDto> trades =
                findTradeUseCase.findTrades(10L, Status.CHAT);

        assertThat(trades).hasSize(2);
        assertThat(trades.get(0).getTradeId()).isEqualTo(trade2.getId());
        assertThat(trades.get(0).getLastChatMessage()).isEqualTo(lastMessage.getMessage());
    }

}