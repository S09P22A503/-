package com.sshmarket.trade.application.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.trade.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final String TOPIC_NAME = "trade";

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME)
    public void listenMessage(String jsonMessage) {
        try {
            MessageDto message = objectMapper.readValue(jsonMessage, MessageDto.class);
            System.out.println(">>>" + message.getName() + "," + message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
