package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.TradeHistory;
import java.time.format.DateTimeFormatter;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeHistoryResponseDto {
    private Long id;
    private Long tradeId;
    private Long articleId;
    private int price;
    private String title;
    private String mainImage;
    private boolean isReviewed;
    private String boughtAt;

    public static TradeHistoryResponseDto from(TradeHistory tradeHistory) {
        return TradeHistoryResponseDto.builder()
                .id(tradeHistory.getId())
                .tradeId(tradeHistory.getTrade().getId())
                .articleId(tradeHistory.getTrade().getArticleId())
                .title(tradeHistory.getTitle())
                .mainImage(tradeHistory.getMainImage())
                .isReviewed(tradeHistory.isReviewed())
                .boughtAt(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(tradeHistory.getCreatedAt()))
                .price(tradeHistory.getPrice())
                .build();
    }
}
