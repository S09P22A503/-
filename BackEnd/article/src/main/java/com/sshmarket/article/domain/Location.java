package com.sshmarket.article.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class Location {
    @Id
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false)
    private String LocationName;

    @OneToMany(mappedBy = "location")
    private List<Article> articles;

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void removeArticle(Article target){
        this.articles.removeIf(article -> article.equals(target));
    }
}
