package com.riobener.userservice.infrastructure.repositories;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteJpaRepository extends JpaRepository<Favorite,Long> {
    Optional<Favorite> deleteAllByUser(User user);
    Optional<Favorite> findByUserIdAndSampleId(Long userId, Long sampleId);
    Iterable<Favorite> findAllByUserId(Long userId);
}
