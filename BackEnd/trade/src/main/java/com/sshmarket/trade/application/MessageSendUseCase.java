package com.sshmarket.trade.application;

import com.sshmarket.trade.application.kafka.KafkaProducer;
import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MessageDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSendUseCase {

    private final KafkaProducer kafkaProducer;
    private final TradeMessageRepository tradeMessageRepository;

    public TradeMessage sendMessage(MessageDto message) {
        kafkaProducer.sendWithCallback(message);
        TradeMessage tradeMessage = TradeMessage.createTradeMessage(message.getMessage(),
                message.getTradeId(),
                message.getMemberId(), LocalDateTime.now());
        tradeMessageRepository.save(tradeMessage);
        return tradeMessage;
    }

}
