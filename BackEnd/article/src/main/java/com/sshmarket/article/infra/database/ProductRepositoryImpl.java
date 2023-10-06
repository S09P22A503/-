package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.ProductRepository;
import com.sshmarket.article.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public List<Product> findProductByItemId(Integer itemId){
        return jpaProductRepository.findByItemId(itemId);
    }
}
