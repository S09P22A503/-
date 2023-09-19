package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.domain.TradeMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTradeMessageUseCase {

    private final TradeMessageRepository tradeMessageRepository;

    public List<TradeMessage> findTradeMessages(Long tradeId) {
        return tradeMessageRepository.findByTradeId(tradeId);
    }
}
