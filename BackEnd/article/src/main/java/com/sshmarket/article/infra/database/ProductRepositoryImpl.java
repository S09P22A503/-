package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.ProductRepository;
import com.sshmarket.article.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public Optional<Product> findById(Long id){
        return jpaProductRepository.findById(id);
    }
}
