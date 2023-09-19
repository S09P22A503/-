package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
}
