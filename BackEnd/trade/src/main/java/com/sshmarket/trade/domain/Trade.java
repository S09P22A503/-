package com.sshmarket.trade.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@ToString
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

    public void sellTrade(){
        this.status = Status.ACCEPT;
    }

    public void finishTrade() {
        if(this.status != Status.ACCEPT) {
            throw new RuntimeException("판매완료 상태가 아닙니다");
        }
        this.status = Status.FINISH;
    }

    public void cancelTrade() {
        if (this.status == Status.FINISH) {
            throw new RuntimeException("이미 구매 확정한 거래입니다.");
        }
        this.status = Status.CANCEL;
    }

    public Long findTrader(Long memberId) {
        if (this.buyerId != memberId) {
            return this.buyerId;
        }
        return this.sellerId;
    }
}