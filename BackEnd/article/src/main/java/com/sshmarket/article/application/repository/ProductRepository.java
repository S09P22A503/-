package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);

}
