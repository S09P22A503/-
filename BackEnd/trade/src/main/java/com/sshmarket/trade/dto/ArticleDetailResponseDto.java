package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.Member;
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

    private List<String> images;

    private String content;

    private String location;

    private Boolean isLike;

    public ArticleDetailResponseDto(Long id, Member member, String title, Integer price, Integer amount, Integer mass, List<String> images, String content, String location, Boolean isLike) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.mass = mass;
        this.images = images;
        this.content = content;
        this.location = location;
        this.isLike = isLike;
    }
}
