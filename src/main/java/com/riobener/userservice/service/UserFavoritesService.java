package com.riobener.userservice.service;

import com.riobener.userservice.entity.UserFavorites;
import com.riobener.userservice.repository.UserFavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoritesService {
    @Autowired
    UserFavoritesRepository userFavoritesRepository;

    public UserFavorites addFavorite(UserFavorites favorites){
        return userFavoritesRepository.save(favorites);
    }
}
