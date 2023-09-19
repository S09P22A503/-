package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaLocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findById(Long id);

}
