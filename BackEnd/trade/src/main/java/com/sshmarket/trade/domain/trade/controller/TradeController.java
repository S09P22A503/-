package com.sshmarket.trade.domain.trade.controller;

import com.sshmarket.trade.domain.trade.application.kafka.KafkaProducer;
import com.sshmarket.trade.domain.trade.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TradeController {

    private final KafkaProducer kafkaProducer;

    @RequestMapping("/publish")
    public String publishJson(MessageDto message) {
        kafkaProducer.sendWithCallback(message);
        return "published a message with callback :" + message.getName() + "," + message.getMessage();
    }
}
