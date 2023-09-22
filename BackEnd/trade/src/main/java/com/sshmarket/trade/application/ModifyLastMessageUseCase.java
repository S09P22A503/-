package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModifyLastMessageUseCase {

    private final TradeRepository tradeRepository;
    private final TradeMessageRepository tradeMessageRepository;

    public void modifyLastMessage(Long tradeId, Long userId) {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(
                () -> new NotFoundResourceException("해당 거래가 존재하지 않습니다.")
        );

        TradeMessage tradeMessage = tradeMessageRepository.findTopByTradeIdOrderByCreatedAtDesc(
                tradeId);
        trade.modifyCheckedMessage(userId, tradeMessage.getMemberId());
    }
}
