package com.sshmarket.trade.application;

import static org.assertj.core.api.Assertions.*;

import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MessageDto;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Transactional
@SpringBootTest
class FindTradeMessageUseCaseTest {

    @Autowired
    private FindTradeMessageUseCase findTradeMessageUseCase;
    @Autowired
    private MessageSendUseCase messageSendUseCase;

    @Test
    void 메시지_리스트_조회() {
        TradeMessage message = messageSendUseCase.sendMessage(new MessageDto("안녕", 10L, 2L));
        TradeMessage message2 = messageSendUseCase.sendMessage(new MessageDto("안녕2", 100L, 2L));
        List<TradeMessage> tradeMessages = findTradeMessageUseCase.listTradeMessage(10L);
        assertThat(tradeMessages).hasSize(1);
    }

}
