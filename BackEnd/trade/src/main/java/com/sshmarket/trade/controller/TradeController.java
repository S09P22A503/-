package com.sshmarket.trade.controller;

import com.sshmarket.trade.application.*;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.*;

import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TradeController {

    private final SendMessageUseCase sendMessageUseCase;
    private final AddTradeUseCase addTradeUseCase;
    private final FindTradeMessageUseCase findTradeMessageUseCase;
    private final FindTradeUseCase findTradeUseCase;
    private final ModifyTradeUseCase modifyTradeUseCase;

    @MessageMapping("/send")
    public void messageSend(MessageDto message) {
        log.info(message.getMessage());
        sendMessageUseCase.sendMessage(message);
    }

    @PostMapping("/trades")
    public ResponseEntity<?> tradeAdd(
            @RequestBody @Valid final TradeCreateRequestDto tradeCreateRequestDto) {
        Trade trade = addTradeUseCase.addTrade(tradeCreateRequestDto);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 생성에 성공했습니다.", trade);
    }

    @GetMapping("/trades/{memberId}")
    public ResponseEntity<?> tradesFind(@PathVariable("memberId") Long memberId, Status status) {
        List<TradesResponseDto> trades = findTradeUseCase.findTrades(memberId, status);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 조회에 성공했습니다.", trades);
    }

    @GetMapping("/trades/{tradeId}/messages")
    public ResponseEntity<?> tradeMessageList(@PathVariable("tradeId") Long tradeId) {
        List<TradeMessage> tradeMessages = findTradeMessageUseCase.findTradeMessages(tradeId);
        return HttpResponse.okWithData(HttpStatus.OK, "채팅방 메시지 조회에 성공했습니다.", tradeMessages);
    }

    @PatchMapping("/trades/{tradeId}/sell")
    public ResponseEntity<?> tradeSell(@PathVariable("tradeId") Long tradeId) {
        modifyTradeUseCase.sellTrade(tradeId);
        return HttpResponse.ok(HttpStatus.OK, "판매 완료 처리되었습니다.");
    }

    @PatchMapping("/trades/{tradeId}/buy")
    public ResponseEntity<?> tradeFinish(@PathVariable("tradeId") Long tradeId) {
        modifyTradeUseCase.finishTrade(tradeId);
        return HttpResponse.ok(HttpStatus.OK, "구매 확정 처리되었습니다.");
    }

    @PatchMapping("/trades/{tradeId}/cancel")
    public ResponseEntity<?> tradeCancel(@PathVariable("tradeId") Long tradeId) {
        modifyTradeUseCase.cancelTrade(tradeId);
        return HttpResponse.ok(HttpStatus.OK, "구매 취소 처리되었습니다.");
    }

}