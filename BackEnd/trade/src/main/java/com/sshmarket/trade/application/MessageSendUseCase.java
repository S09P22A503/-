package com.sshmarket.trade.application;

import com.sshmarket.trade.application.kafka.KafkaProducer;
import com.sshmarket.trade.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSendUseCase {

    private final KafkaProducer kafkaProducer;

    public void sendMessage(MessageDto message) {
        kafkaProducer.sendWithCallback(message);
    }

}
