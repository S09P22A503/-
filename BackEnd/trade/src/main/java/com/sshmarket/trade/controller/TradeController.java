package com.sshmarket.trade.controller;

import com.sshmarket.trade.application.kafka.KafkaProducer;
import com.sshmarket.trade.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TradeController {

    private final KafkaProducer kafkaProducer;

    @RequestMapping("/publish")
    public String publishJson(MessageDto message) {
        kafkaProducer.sendWithCallback(message);
        return "published a message with callback :" + message.getName() + ","
                + message.getMessage();
    }

    @MessageMapping("/hello")
    public void message(MessageDto message) {
        log.info(message.getMessage());
    }
}
