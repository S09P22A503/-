package com.sshmarket.trade.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime tradedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long checkedMessageIdSeller;

    @Column(nullable = false)
    private Long checkedMessageIdBuyer;

    @Builder
    private Trade(LocalDateTime tradedAt, Status status, Long articleId, Long buyerId,
            Long sellerId,
            Long checkedMessageIdSeller, Long checkedMessageIdBuyer) {
        this.tradedAt = tradedAt;
        this.status = status;
        this.articleId = articleId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.checkedMessageIdSeller = checkedMessageIdSeller;
        this.checkedMessageIdBuyer = checkedMessageIdBuyer;
    }

    public static Trade createTrade(LocalDateTime tradedAt, Status status, Long articleId,
            Long buyerId, Long sellerId,
            Long checkedMessageIdSeller, Long checkedMessageIdBuyer) {
        return Trade.builder()
                    .tradedAt(tradedAt)
                    .status(status)
                    .articleId(articleId)
                    .buyerId(buyerId)
                    .sellerId(sellerId)
                    .checkedMessageIdSeller(checkedMessageIdSeller)
                    .checkedMessageIdBuyer(checkedMessageIdBuyer)
                    .build();
    }
}