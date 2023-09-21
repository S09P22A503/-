package com.sshmarket.trade.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private boolean isReviewed;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 2000)
    private String mainImage;

    @Column(nullable = false, length = 500)
    private String title;

    @Builder
    private TradeHistory(Trade trade, boolean isReviewed, int price,
                         String mainImage, String title) {
        this.trade = trade;
        this.isReviewed = isReviewed;
        this.price = price;
        this.mainImage = mainImage;
        this.title = title;
    }

    public static TradeHistory createTradeHistory(Trade trade,
                                                  boolean isReviewed, int price,
                                                  String mainImage, String title) {
        return TradeHistory.builder()
                .trade(trade)
                .isReviewed(isReviewed)
                .price(price)
                .mainImage(mainImage)
                .title(title)
                .build();
    }
}