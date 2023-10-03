package com.sshmarket.review.application.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

    private Long id;
    private String name;

    public Article(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
