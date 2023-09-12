package com.sshmarket.trade.global.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaProduceService kafkaProduceService;

    @RequestMapping("/publish")
    public String publishJson(MyMessage message) {
        kafkaProduceService.sendWithCallback(message);
        return "published a message with callback :" + message.getName() + "," + message.getMessage();
    }
}
