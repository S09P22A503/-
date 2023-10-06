package com.sshmarket.review.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

    private Long id;
    private String title;

    @Builder
    public Article(Long id, String title) {
        this.id = id;
        this.title = title;
    }

}
