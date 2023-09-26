package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.TradeMessage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSearchResponseDto {

    Long tradeId;
    MemberResponseDto member;
    String messageId;
    String lastChatMessage;
    LocalDateTime createdAt;

    public static TradeSearchResponseDto from(TradeMessage tradeMessage, MemberResponseDto member) {
        return TradeSearchResponseDto.builder()
                .tradeId(tradeMessage.getTradeId())
                .messageId(tradeMessage.getId())
                .lastChatMessage(tradeMessage.getMessage())
                .createdAt(tradeMessage.getCreatedAt())
                .member(member).build();
    }

}
