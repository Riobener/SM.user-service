package com.riobener.userservice.domain.services;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.exceptions.EmptyUserFavoritesException;
import com.riobener.userservice.domain.model.exceptions.FavoriteAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;
import com.riobener.userservice.infrastructure.dto.request.CreateFavoriteDto;

public interface FavoriteServiceInterface {
    Favorite createFavorite(CreateFavoriteDto dto) throws FavoriteAlreadyExistException, UserNotFoundException;
    Favorite deleteFavorite(Long favoriteId) throws FavoriteNotFoundException;
    Iterable<Favorite> findAllFavoritesByUser(Long userId) throws EmptyUserFavoritesException, UserNotFoundException;

    Iterable<Favorite> deleteAllFavoritesByUserId(Long userId) throws EmptyUserFavoritesException,
            UserNotFoundException;
}
