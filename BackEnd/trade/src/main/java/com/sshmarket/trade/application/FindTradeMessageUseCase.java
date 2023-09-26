package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Member;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.domain.service.MemberService;
import com.sshmarket.trade.domain.service.TradeService;
import com.sshmarket.trade.dto.MemberResponseDto;
import com.sshmarket.trade.dto.TradeMessageResponseDto;
import com.sshmarket.trade.dto.TradeSearchResponseDto;
import com.sshmarket.trade.infra.jwt.JwtTranslator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTradeMessageUseCase {

    private final TradeMessageRepository tradeMessageRepository;
    private final TradeRepository tradeRepository;
    private final JwtTranslator jwtTranslator;
    private final MemberService memberService;
    private final TradeService tradeService;

    public List<TradeMessageResponseDto> findTradeMessages(Long tradeId) {
        return tradeMessageRepository.findByTradeId(tradeId).stream()
                                     .map(TradeMessageResponseDto::from).toList();
    }

    public List<TradeSearchResponseDto> findTradesByKeyword(String keyword, String token,
            String statusString) {
        Member me = jwtTranslator.translate(token);
        Status status = Status.valueOf(statusString);
        List<Trade> trades;
        if (status == Status.ALL) {
            trades = tradeRepository.findByMemberId(me.getId());
        } else {
            trades = tradeRepository.findByMemberIdAndStatus(me.getId(), status);
        }

        Map<Long, Trade> tradesMap = trades.stream().collect(
                Collectors.toMap(Trade::getId,
                        Trade -> Trade));

        Map<Long, MemberResponseDto> members = memberService.getMembersMap(trades, me.getId());

        List<TradeMessage> allMessages = tradeMessageRepository.findByMessageContainingAndTradeIdIn(
                keyword, trades.stream().map(Trade::getId).toList());

        List<TradeMessage> latestTradeMessages = tradeService.getLatestTradeMessages(keyword,
                allMessages);

        return tradeService.getTradeSearchResponse(trades, keyword, members, tradesMap, me.getId(),
                latestTradeMessages);
    }

}
