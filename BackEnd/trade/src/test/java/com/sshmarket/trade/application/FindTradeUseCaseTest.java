package com.sshmarket.trade.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MessageDto;
import com.sshmarket.trade.dto.TradeCreateRequestDto;
import java.util.List;
import javax.transaction.Transactional;

import com.sshmarket.trade.dto.TradesResponseDto;
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
        TradeMessage lastMessage = sendMessageUseCase.sendMessage(new MessageDto("마지막 메시지", trade.getId(), trade.getBuyerId()));

        Trade trade2 = tradeUseCase.addTrade(new TradeCreateRequestDto(3L, 40L, 50L));
        TradeMessage message2 = sendMessageUseCase.sendMessage(new MessageDto("안녕하세요", trade2.getId(), trade2.getBuyerId()));

        List<TradesResponseDto> trades =
                findTradeUseCase.findTrades(trade.getBuyerId(), Status.CHAT);

        assertThat(trades).hasSize(1);
        assertThat(trades.get(0).getTradeId()).isEqualTo(trade.getId());
        assertThat(trades.get(0).getLastChatMessage()).isEqualTo(lastMessage.getMessage());
    }

}