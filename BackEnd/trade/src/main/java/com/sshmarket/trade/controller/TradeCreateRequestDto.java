package com.sshmarket.trade.controller;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeCreateRequestDto {

    @NotNull
    private Long articleId;

    @NotNull
    private Long buyerId;

    @NotNull
    private Long sellerId;

    public TradeCreateRequestDto(Long articleId, Long buyerId, Long sellerId) {
        this.articleId = articleId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
    }
}
