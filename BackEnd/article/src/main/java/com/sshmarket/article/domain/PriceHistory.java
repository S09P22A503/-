package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceHistory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_history_id")
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer wholesalePrice;

    @Column(nullable = false)
    private Integer retailPrice;

    @Builder
    private PriceHistory(Product product, LocalDateTime date, Integer wholesalePrice, Integer retailPrice) {
        this.product = product;
        this.date = date;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
    }

    public static PriceHistory createPriceHistory(Product product, LocalDateTime date, Integer wholesalePrice, Integer retailPrice) {
        PriceHistory history = PriceHistory.builder()
                .product(product)
                .date(date)
                .wholesalePrice(wholesalePrice)
                .retailPrice(retailPrice)
                .build();

        product.addPriceHistory(history);
        return history;
    }
}
