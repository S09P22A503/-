package com.sshmarket.trade.dto;

import com.sshmarket.trade.domain.Member;
import java.util.LinkedHashMap;
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

    public static ArticleDetailResponseDto from(LinkedHashMap articleResponse) {
        return ArticleDetailResponseDto.builder()
                .id(new Long((Integer)articleResponse.get("id")))
                .title((String)articleResponse.get("title"))
                .price((Integer) articleResponse.get("price"))
                .amount((Integer) articleResponse.get("amount"))
                .mass((Integer) articleResponse.get("mass"))
                .images((List<String>) articleResponse.get("images"))
                .content((String) articleResponse.get("content"))
                .location((String) articleResponse.get("location"))
                .isLike((Boolean) articleResponse.get("isLike"))
                .build();
    }
}
