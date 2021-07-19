package com.riobener.userservice.service;

import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.FavoriteNotFoundException;
import com.riobener.userservice.model.FavoriteModel;
import com.riobener.userservice.repository.FavoriteRepository;
import com.riobener.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceQL {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;

    public Favorite createFavorite(Long sampleId, User user){
        Favorite favorite = new Favorite();
        favorite.setSampleId(sampleId);
        favorite.setUser(user);
        return favoriteRepository.save(favorite);
    }

    public Favorite deleteFavoriteById(Long id) throws FavoriteNotFoundException {
        Favorite favorite = favoriteRepository.findById(id).get();
        if (favorite == null) {
            throw new FavoriteNotFoundException("Пользователь не добавлял эту запись");
        }else{
            favoriteRepository.deleteById(favorite.getId());
        }
        return favorite;
    }

    public List<Favorite> getAllFavorites(int count) {
        return favoriteRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public Optional<Favorite> getFavorite(Long id) {
        return favoriteRepository.findById(id);
    }
}
