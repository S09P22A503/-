package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.dto.TradeCreateRequestDto;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTradeUseCase {

    private final TradeRepository tradeRepository;

    public Trade addTrade(TradeCreateRequestDto tradeCreateRequestDto) {

        // 거래 채팅방 존재 - articleId, buyerId, status(FINISH, CANCEL)이 아닌 경우
        Trade trade = tradeRepository.findByArticleIdAndBuyerIdAndStatusNotIn(
                tradeCreateRequestDto.getArticleId(), tradeCreateRequestDto.getBuyerId(),
                Arrays.asList(Status.FINISH, Status.CANCEL));

        // 거래 채팅방 없으면 생성
        if (Objects.isNull(trade)) {
            trade = Trade.createTrade(LocalDateTime.now(), Status.CHAT,
                    tradeCreateRequestDto.getArticleId(),
                    tradeCreateRequestDto.getBuyerId(), tradeCreateRequestDto.getSellerId(),
                    0L, 0L);
            tradeRepository.save(trade);
        }

        return trade;
    }
}
