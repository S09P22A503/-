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
public class TradesResponseDto {

    private Long tradeId;
    private MemberResponseDto memberResponseDto;
    private String lastChatMessage;
    private LocalDateTime createdAt;

    public static TradesResponseDto from(Trade trade, TradeMessage tradeMessage, MemberResponseDto memberResponseDto) {
        return TradesResponseDto.builder()
                .tradeId(trade.getId())
                .lastChatMessage(tradeMessage.getMessage())
                .createdAt(tradeMessage.getCreatedAt())
                .memberResponseDto(memberResponseDto)
                .build();
    }
}
