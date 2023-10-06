package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.LocationRepository;
import com.sshmarket.article.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    private final JpaLocationRepository jpaLocationRepository;

    @Override
    public Optional<Location> findLocationById(Long id){
        return jpaLocationRepository.findById(id);
    }
}
