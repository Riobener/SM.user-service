package com.riobener.userservice.service;

import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.FavoriteNotFoundException;
import com.riobener.userservice.exception.UserNotFoundException;
import com.riobener.userservice.model.FavoriteModel;
import com.riobener.userservice.repository.FavoriteRepository;
import com.riobener.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;

    public FavoriteModel createFavorite(Favorite favorite, @RequestParam Long userId){
        User user = userRepository.findById(userId).get();
        favorite.setUser(user);
        return FavoriteModel.toModel(favoriteRepository.save(favorite));
    }

    public FavoriteModel deleteFavoriteById(Long id) throws FavoriteNotFoundException {
        Favorite favorite = favoriteRepository.findById(id).get();
        if (favorite == null) {
            throw new FavoriteNotFoundException("Пользователь не добавлял эту запись");
        }else{
            favoriteRepository.deleteById(favorite.getId());
        }
        return FavoriteModel.toModel(favorite);
    }
}
