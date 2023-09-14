package com.sshmarket.trade.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TradeHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private boolean isReviewed;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 2000)
    private String mainImage;

    @Column(nullable = false, length = 500)
    private String title;

    @Builder
    private TradeHistory(Trade trade, Long articleId, Long buyerId, boolean isReviewed, int price,
            String mainImage, String title) {
        this.trade = trade;
        this.articleId = articleId;
        this.buyerId = buyerId;
        this.isReviewed = isReviewed;
        this.price = price;
        this.mainImage = mainImage;
        this.title = title;
    }

    public static TradeHistory createTradeHistory(Trade trade, Long articleId, Long buyerId,
            boolean isReviewed, int price,
            String mainImage, String title) {
        return TradeHistory.builder()
                           .trade(trade)
                           .articleId(articleId)
                           .buyerId(buyerId)
                           .isReviewed(isReviewed)
                           .price(price)
                           .mainImage(mainImage)
                           .title(title)
                           .build();
    }
}