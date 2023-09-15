package com.sshmarket.trade.controller;

import com.sshmarket.trade.application.SendMessageUseCase;
import com.sshmarket.trade.dto.MessageDto;
import com.sshmarket.trade.dto.TradeCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TradeController {

    private final SendMessageUseCase sendMessageUseCase;

    @MessageMapping("/send")
    public void messageSend(MessageDto message) {
        log.info(message.getMessage());
        sendMessageUseCase.sendMessage(message);
    }

    @PatchMapping("/trades/{tradeId}/sell")
    public void tradeSell(@PathVariable("tradeId")Long tradeId) {

    }
}
