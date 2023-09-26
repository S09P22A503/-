package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.TradeMessage;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TradeMessageResponseDto {

    private String id;
    private String message;
    private Long tradeId;
    private Long memberId;
    private LocalDateTime createdAt;

    public static TradeMessageResponseDto from(TradeMessage tradeMessage) {
        return TradeMessageResponseDto.builder()
                                      .id(tradeMessage.getId())
                                      .tradeId(tradeMessage.getTradeId())
                                      .memberId(tradeMessage.getMemberId())
                                      .message(tradeMessage.getMessage())
                                      .createdAt(tradeMessage.getCreatedAt()).build();
    }


}
