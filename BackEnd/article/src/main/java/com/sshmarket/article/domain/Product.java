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

    private String name;

    private String wholesaleUnit;

    private Double wholesaleMass;

    private String retailUnit;

    private Double retailMass;

    private Integer itemId;

    private String itemName;

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
