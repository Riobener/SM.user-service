package com.riobener.userservice.domain.services;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.exceptions.FavoriteAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;

import java.util.Optional;

public interface FavoriteServiceInterface {
    Favorite createFavorite(Favorite favorite) throws FavoriteAlreadyExistException;
    Optional<Favorite> deleteFavorite(Long favoriteId) throws FavoriteNotFoundException;
    Iterable<Favorite> findAllFavoritesByUser(Long userId);
}
