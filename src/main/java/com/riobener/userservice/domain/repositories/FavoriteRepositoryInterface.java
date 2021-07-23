package com.riobener.userservice.domain.repositories;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;

import java.util.Optional;

public interface FavoriteRepositoryInterface {
    Favorite create(Favorite favorite);

    Optional<Favorite> findById(Long id);
    Optional<Favorite> findByUserAndSampleId(Long userId, Long sampleId);
    Iterable<Favorite> findAllByUser(Long userId);
    void delete(Long favoriteId);

    void deleteAllByUser(User user);
}
