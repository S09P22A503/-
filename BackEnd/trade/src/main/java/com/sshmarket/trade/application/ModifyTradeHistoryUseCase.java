package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeHistoryRepository;
import com.sshmarket.trade.domain.TradeHistory;
import com.sshmarket.trade.dto.TradeHistoryResponseDto;
import com.sshmarket.trade.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyTradeHistoryUseCase {

    private final TradeHistoryRepository tradeHistoryRepository;

    public void successReview(Long tradeHistoryId) {
       TradeHistory tradeHistory = tradeHistoryRepository.findByTradeBuyerId(tradeHistoryId).orElseThrow(
                () -> new NotFoundResourceException("해당 거래이력이 존재하지 않습니다.")
        );
        tradeHistory.successReview();
    }
}
