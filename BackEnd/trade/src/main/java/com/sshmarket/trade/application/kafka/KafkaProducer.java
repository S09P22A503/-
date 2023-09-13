package com.sshmarket.trade.application.kafka;

import com.sshmarket.trade.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String TOPIC_NAME = "trade";

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void sendWithCallback(MessageDto message) {
        ListenableFuture<SendResult<String, MessageDto>> future = kafkaTemplate.send(TOPIC_NAME,
                message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, MessageDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed " + message + " due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MessageDto> result) {
                System.out.println(
                        "Sent " + message + " offset:" + result.getRecordMetadata().offset());
            }
        });
    }
}
