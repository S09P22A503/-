package com.sshmarket.trade.global.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProduceService {

	private static final String TOPIC_NAME = "trade";

	@Autowired
	private KafkaTemplate<String, MyMessage> kafkaTemplate;


    public void sendWithCallback(MyMessage message) {
        ListenableFuture<SendResult<String, MyMessage>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.addCallback(
            new ListenableFutureCallback<SendResult<String, MyMessage>>() {
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed " + message + " due to : " + ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, MyMessage> result) {
                    System.out.println("Sent " + message + " offset:" + result.getRecordMetadata().offset());
                }
            });
    }
}
