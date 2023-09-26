package com.sshmarket.trade.application;

import static org.junit.jupiter.api.Assertions.*;

import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.KafkaMessageDto;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class MessageSendUseCaseTest {

    @Autowired
    private SendMessageUseCase sendMessageUseCase;

    @Test
    void 메시지가_저장된다() {
        TradeMessage message = sendMessageUseCase.sendMessage(new KafkaMessageDto("안녕", 1L, 2L));
        assertNotNull(message);
    }

}