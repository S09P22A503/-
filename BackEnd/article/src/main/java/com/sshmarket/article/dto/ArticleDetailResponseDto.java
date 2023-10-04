package com.sshmarket.article.dto;

import com.sshmarket.article.domain.TradeType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ArticleDetailResponseDto {

    private Long id;

    private Member member;

    private String title;

    private Integer price;

    private Integer amount;

    private Integer mass;

    private String mainImage;

    private List<String> images;

    private Integer itemId;

    private String content;

    private String location;

    private TradeType tradeType;

    private Boolean isLike;

    public ArticleDetailResponseDto(Long id, Member member, String title, Integer price, Integer amount, Integer mass, String mainImage,
                                    List<String> images, Integer itemId, String content, String location, Boolean isLike) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.mass = mass;
        this.mainImage = mainImage;
        this.images = images;
        this.itemId = itemId;
        this.content = content;
        this.location = location;
        this.isLike = isLike;
    }
}
