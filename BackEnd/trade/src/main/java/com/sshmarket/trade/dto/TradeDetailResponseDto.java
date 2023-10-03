package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TradeDetailResponseDto {

    private String articleTitle;
    private Integer price;
    private Status status;
    private Boolean isSeller;
    private Long articleId;

    public static TradeDetailResponseDto from(Trade trade, Long userId,
            ArticleDetailResponseDto article) {
        return TradeDetailResponseDto.builder()
                                     .articleTitle(article.getTitle())
                                     .price(article.getPrice())
                                     .status(trade.getStatus())
                                     .isSeller(userId.equals(trade.getSellerId()) ? true : false)
                                     .articleId(article.getId())
                                     .build();
    }
}
