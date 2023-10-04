package com.sshmarket.trade.controller;

import com.sshmarket.trade.application.AddTradeHistoryUseCase;
import com.sshmarket.trade.application.AddTradeUseCase;
import com.sshmarket.trade.application.FindTradeHistoryUseCase;
import com.sshmarket.trade.application.FindTradeMessageUseCase;
import com.sshmarket.trade.application.FindTradeUseCase;
import com.sshmarket.trade.application.ModifyTradeUseCase;
import com.sshmarket.trade.application.SendMessageUseCase;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.dto.*;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TradeController {

    private final SendMessageUseCase sendMessageUseCase;
    private final AddTradeUseCase addTradeUseCase;
    private final AddTradeHistoryUseCase addTradeHistoryUseCase;
    private final FindTradeMessageUseCase findTradeMessageUseCase;
    private final FindTradeUseCase findTradeUseCase;
    private final FindTradeHistoryUseCase findTradeHistoryUseCase;
    private final ModifyTradeUseCase modifyTradeUseCase;

    //trade/pub/send로 요청한거 처리
    @MessageMapping("/send")
    public void messageSend(KafkaMessageDto message) {
        log.info(message.getMessage());
        sendMessageUseCase.sendMessage(message);
    }

    @PostMapping("/trades")
    public ResponseEntity<?> tradeAdd(
            @RequestBody @Valid final TradeCreateRequestDto tradeCreateRequestDto) {
        Trade trade = addTradeUseCase.addTrade(tradeCreateRequestDto);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 생성에 성공했습니다.", trade.getId());
    }

    @GetMapping("/trades/{memberId}")
    public ResponseEntity<?> tradesFind(@PathVariable("memberId") Long memberId, Status status) {
        List<TradeResponseDto> trades = findTradeUseCase.findTrades(memberId, status);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 조회에 성공했습니다.", trades);
    }

    @GetMapping("/trades/{tradeId}/messages")
    public ResponseEntity<?> tradeMessageList(@PathVariable("tradeId") Long tradeId) {
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 메시지 조회에 성공했습니다.",
                findTradeMessageUseCase.findTradeMessages(tradeId));
    }

    @PatchMapping("/trades/{tradeId}/sell")
    public ResponseEntity<?> tradeSell(@PathVariable("tradeId") Long tradeId) {
        modifyTradeUseCase.sellTrade(tradeId);
        return HttpResponse.ok(HttpStatus.OK, "판매 완료 처리되었습니다.");
    }

    @PostMapping("/trades/{tradeId}/buy")
    public ResponseEntity<?> tradeFinish(@PathVariable("tradeId") Long tradeId,
            @RequestBody @Valid TradeHistoryCreateRequestDto tradeHistoryCreateRequestDto) {
        Trade trade = modifyTradeUseCase.finishTrade(tradeId);
        addTradeHistoryUseCase.addTradeHistory(trade, tradeHistoryCreateRequestDto);
        return HttpResponse.ok(HttpStatus.OK, "구매 확정 처리되었습니다.");
    }

    @PatchMapping("/trades/{tradeId}/cancel")
    public ResponseEntity<?> tradeCancel(@PathVariable("tradeId") Long tradeId) {
        modifyTradeUseCase.cancelTrade(tradeId);
        return HttpResponse.ok(HttpStatus.OK, "구매 취소 처리되었습니다.");
    }

    @GetMapping("/trades/history/{memberId}")
    public ResponseEntity<?> tradeHistoryFind(@PathVariable("memberId") Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<TradeHistoryResponseDto> tradeHistoryList = findTradeHistoryUseCase.findTradeHistory(
                memberId, pageable);
        return HttpResponse.okWithData(HttpStatus.OK, "거래 내역 조회에 성공했습니다.", tradeHistoryList);
    }

    @GetMapping("/trades/search/{keyword}")
    public ResponseEntity<?> tradeFindByMessage(@PathVariable("keyword") String keyword,
            @RequestParam("status")
            String status, @CookieValue(value = "jwt", required = true) String token) {
        List<TradeSearchResponseDto> trades = findTradeMessageUseCase.findTradesByKeyword(
                keyword, token, status);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 검색 성공했습니다.", trades);
    }

    @GetMapping("/trades/detail/{tradeId}")
    public ResponseEntity<?> tradeDetail(@PathVariable("tradeId") Long tradeId,
            @CookieValue(value = "jwt", required = true) String token) {
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 검색 성공했습니다.", findTradeUseCase.findTradeDetail(tradeId, token));
    }

    @GetMapping("/trades/trader/{tradeId}")
    public ResponseEntity<?> traderDetail(@PathVariable("tradeId") Long tradeId,
                                          @CookieValue(value = "jwt", required = true) String token) {
        MemberResponseDto trader = findTradeUseCase.findTrader(tradeId, token);
        log.info("traderDetail", trader);
        return HttpResponse.okWithData(HttpStatus.OK, "상대방 프로필 조회 성공했습니다.", trader);
    }
}