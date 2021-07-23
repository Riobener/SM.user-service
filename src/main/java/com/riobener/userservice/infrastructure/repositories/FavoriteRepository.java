package com.riobener.userservice.infrastructure.repositories;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.repositories.FavoriteRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FavoriteRepository implements FavoriteRepositoryInterface {

    @Autowired
    FavoriteJpaRepository favoriteJPARepository;

    @Override
    public Favorite create(Favorite favorite) {
        return favoriteJPARepository.save(favorite);
    }


    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteJPARepository.findById(id);
    }

    @Override
    public Optional<Favorite> findByUserAndSampleId(Long userId, Long sampleId) {
        return favoriteJPARepository.findByUserIdAndSampleId(userId,sampleId);
    }

    @Override
    public Iterable<Favorite> findAllByUser(Long userId) {
        return favoriteJPARepository.findAllByUserId(userId);
    }

    @Override
    public void delete(Long favoriteId) {
        favoriteJPARepository.deleteById(favoriteId);
    }

    @Override
    public void deleteAllByUser(User user) {
        favoriteJPARepository.deleteAllByUser(user);
    }
}
