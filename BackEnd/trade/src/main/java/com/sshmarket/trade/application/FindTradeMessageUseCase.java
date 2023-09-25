package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Member;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.domain.TradeType;
import com.sshmarket.trade.dto.TradeSearchResponseDto;
import com.sshmarket.trade.infra.jwt.JwtTranslator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTradeMessageUseCase {

    private final TradeMessageRepository tradeMessageRepository;
    private final TradeRepository tradeRepository;
    private final JwtTranslator jwtTranslator;

    public List<TradeMessage> findTradeMessages(Long tradeId) {
        return tradeMessageRepository.findByTradeId(tradeId);
    }

    public List<TradeSearchResponseDto> findTradesByKeyword(String keyword, String token, TradeType tradeType) {
        Long userId = jwtTranslator.getUserId(token);
        if (tradeType == TradeType.ALL) {
            List<Trade> trades = tradeRepository.findByMemberId(userId);
        }
        else {
            List<Trade> trades = tradeRepository.findByMemberIdAndStatus(userId, tradeType);
        }
    }
}
