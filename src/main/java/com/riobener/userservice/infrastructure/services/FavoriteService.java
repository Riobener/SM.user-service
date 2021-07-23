package com.riobener.userservice.infrastructure.services;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.exceptions.FavoriteAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;
import com.riobener.userservice.domain.services.FavoriteServiceInterface;
import com.riobener.userservice.infrastructure.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FavoriteService implements FavoriteServiceInterface {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Override
    public Favorite createFavorite(Favorite favorite) throws FavoriteAlreadyExistException {
        boolean favoriteIsExist = favoriteRepository.findByUserAndSampleId(favorite.getUser().getId(),
                favorite.getSampleId()).isPresent();
        if(favoriteIsExist){
            throw new FavoriteAlreadyExistException("Данный элемент уже добавлен в избранное!");
        }
        return favoriteRepository.create(favorite);
    }

    @Override
    public Optional<Favorite> deleteFavorite(Long favoriteId) throws FavoriteNotFoundException {
        boolean favoriteIsExist = favoriteRepository.findById(favoriteId).isPresent();
        if(!favoriteIsExist){
            throw new FavoriteNotFoundException("Данного элемента не существует");
        }
        Optional<Favorite> favorite = favoriteRepository.findById(favoriteId);
        favoriteRepository.delete(favoriteId);
        return favorite;
    }

    @Override
    public Iterable<Favorite> findAllFavoritesByUser(Long userId) {
        return favoriteRepository.findAllByUser(userId);
    }
}
