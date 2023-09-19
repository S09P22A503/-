package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Location;

import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findLocationById(Long id);

}
