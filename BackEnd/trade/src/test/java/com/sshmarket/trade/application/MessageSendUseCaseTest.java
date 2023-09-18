package com.sshmarket.trade.application;

import static org.junit.jupiter.api.Assertions.*;

import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MessageDto;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class MessageSendUseCaseTest {

    @Autowired
    private MessageSendUseCase messageSendUseCase;

    @Test
    void 메시지가_저장된다() {
        TradeMessage message = messageSendUseCase.sendMessage(new MessageDto("안녕", 1L, 2L));
        assertNotNull(message);
    }

}