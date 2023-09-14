package com.sshmarket.trade.domain;

import java.time.LocalDateTime;
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
    private String id;
    private String message;
    private Long tradeId;
    private Long memberId;
    private LocalDateTime createdAt;

    @Builder
    private TradeMessage(String message, Long tradeId, Long memberId, LocalDateTime createdAt) {
        this.message = message;
        this.tradeId = tradeId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }

    public static TradeMessage createTradeMessage(String message, Long tradeId, Long memberId,
            LocalDateTime createdAt) {
        return TradeMessage.builder()
                           .message(message)
                           .tradeId(tradeId)
                           .memberId(memberId)
                           .createdAt(createdAt)
                           .build();
    }
}
