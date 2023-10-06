package com.sshmarket.trade.application.kafka;

import com.sshmarket.trade.dto.KafkaMessageDto;
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

    private final KafkaTemplate<String, KafkaMessageDto> kafkaTemplate;

    public void sendWithCallback(KafkaMessageDto message) {
        ListenableFuture<SendResult<String, KafkaMessageDto>> future = kafkaTemplate.send(TOPIC_NAME,
                message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaMessageDto>>() {
            @Override
            public void onFailure(Throwable ex) {
            }

            @Override
            public void onSuccess(SendResult<String, KafkaMessageDto> result) {
            }
        });
    }
}
