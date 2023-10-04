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

    private String mainImage;

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
                .mainImage((String) articleResponse.get("mainImage"))
                .build();
    }
}
