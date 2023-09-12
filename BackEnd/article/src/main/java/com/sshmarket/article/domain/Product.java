package com.sshmarket.article.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
}
