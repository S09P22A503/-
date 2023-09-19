package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;    //품종명

    @Column(nullable = false)
    private String wholesaleUnit;

    @Column(nullable = false)
    private Double wholesaleMass;

    @Column(nullable = false)
    private String retailUnit;

    @Column(nullable = false)
    private Double retailMass;

    @Column(nullable = false)
    private Integer itemId;     //품목 코드

    @Column(nullable = false)
    private String itemName;    //품목명

    @OneToMany(mappedBy = "product")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<PriceHistory> historyList = new ArrayList<>();

    @Builder
    private Product(String name, String wholesaleUnit, Double wholesaleMass, String retailUnit, Double retailMass, Integer itemId, String itemName) {
        this.name = name;
        this.wholesaleUnit = wholesaleUnit;
        this.wholesaleMass = wholesaleMass;
        this.retailUnit = retailUnit;
        this.retailMass = retailMass;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public static Product createProduct(String name, String wholesaleUnit, Double wholesaleMass, String retailUnit, Double retailMass, Integer itemId, String itemName) {
        Product product = Product.builder()
                    .name(name)
                    .wholesaleUnit(wholesaleUnit)
                    .wholesaleMass(wholesaleMass)
                    .retailUnit(retailUnit)
                    .retailMass(retailMass)
                    .itemId(itemId)
                    .itemName(itemName)
                .build();
         return product;
    }

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void addPriceHistory(PriceHistory priceHistory) {
        this.historyList.add(priceHistory);
    }

    public void removeArticle(Article target){
        this.articles.removeIf(article -> article.equals(target));
    }
}
