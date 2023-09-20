package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponseDto {

    private Long tradeId;
    private MemberResponseDto memberResponseDto;
    private String lastChatMessage;
    private LocalDateTime createdAt;

    public static TradeResponseDto from(Trade trade, TradeMessage tradeMessage, MemberResponseDto memberResponseDto) {
        return TradeResponseDto.builder()
                .tradeId(trade.getId())
                .lastChatMessage(tradeMessage.getMessage())
                .createdAt(tradeMessage.getCreatedAt())
                .memberResponseDto(memberResponseDto)
                .build();
    }
}
