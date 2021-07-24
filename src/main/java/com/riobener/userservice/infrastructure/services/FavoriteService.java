package com.riobener.userservice.infrastructure.services;

import com.google.common.collect.Iterables;
import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.*;
import com.riobener.userservice.domain.model.value_objects.UserInfo;
import com.riobener.userservice.domain.services.FavoriteServiceInterface;
import com.riobener.userservice.infrastructure.dto.request.CreateFavoriteDto;
import com.riobener.userservice.infrastructure.repositories.FavoriteRepository;
import com.riobener.userservice.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FavoriteService implements FavoriteServiceInterface {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Favorite createFavorite(CreateFavoriteDto dto) throws FavoriteAlreadyExistException,
            UserNotFoundException {
        boolean favoriteIsExist = favoriteRepository.findByUserAndSampleId(dto.getUserId(),
                dto.getSampleId()).isPresent();
        boolean userExists = userRepository.findById(dto.getUserId()).isPresent();
        if(favoriteIsExist){
            throw new FavoriteAlreadyExistException("Данный элемент уже добавлен в избранное!");
        }else if(!userExists){
            throw new UserNotFoundException("Пользователь не найден");
        }
        User user = userRepository.findById(dto.getUserId()).get();
        Favorite favorite = new Favorite(dto.getSampleId(),
                user
        );
        return favoriteRepository.create(favorite);
    }

    @Override
    public Favorite deleteFavorite(Long favoriteId) throws FavoriteNotFoundException {
        boolean favoriteIsExist = favoriteRepository.findById(favoriteId).isPresent();
        if(!favoriteIsExist){
            throw new FavoriteNotFoundException("Данного элемента не существует");
        }
        Favorite favorite = favoriteRepository.findById(favoriteId).get();
        favoriteRepository.delete(favoriteId);
        return favorite;
    }

    @Override
    public Iterable<Favorite> findAllFavoritesByUser(Long userId) throws EmptyUserFavoritesException,
            UserNotFoundException {
        Iterable<Favorite> favorites = favoriteRepository.findAllByUser(userId);
        boolean userExists = userRepository.findById(userId).isPresent();
        if (Iterables.size(favorites) == 0) {
            throw new EmptyUserFavoritesException("Список избранных пуст");
        }else if(!userExists){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return favorites;
    }
    @Transactional
    @Override
    public Iterable<Favorite> deleteAllFavoritesByUserId(Long userId) throws EmptyUserFavoritesException,
            UserNotFoundException {
        Iterable<Favorite> favorites = favoriteRepository.findAllByUser(userId);
        boolean userExists = userRepository.findById(userId).isPresent();
        if (Iterables.size(favorites) == 0) {
            throw new EmptyUserFavoritesException("Список избранных пуст");
        }else if(!userExists){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return favoriteRepository.deleteAllByUserId(userId);
    }
}
