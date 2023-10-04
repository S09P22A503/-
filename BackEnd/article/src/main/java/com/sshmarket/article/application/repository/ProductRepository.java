package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findProductByItemId(Integer itemId);

}
