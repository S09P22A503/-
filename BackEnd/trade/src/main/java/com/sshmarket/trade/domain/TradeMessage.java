package com.sshmarket.trade.domain;

import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TradeMessage {

    @Id
    private Long id;
    private String message;
    private Long tradeId;
    private Long memberId;

    @Builder
    private TradeMessage(String message, Long tradeId, Long memberId) {
        this.message = message;
        this.tradeId = tradeId;
        this.memberId = memberId;
    }

    public static TradeMessage createTradeMessage(String message, Long tradeId, Long memberId) {
        return TradeMessage.builder()
                           .message(message)
                           .tradeId(tradeId)
                           .memberId(memberId)
                           .build();
    }
}
