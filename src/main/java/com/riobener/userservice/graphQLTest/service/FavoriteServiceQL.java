/*

package com.riobener.userservice.graphQLTest.service;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

*/
