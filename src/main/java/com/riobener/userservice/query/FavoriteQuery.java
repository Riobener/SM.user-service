package com.riobener.userservice.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.service.FavoriteService;
import com.riobener.userservice.service.FavoriteServiceQL;
import com.riobener.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class FavoriteQuery implements GraphQLQueryResolver {
    @Autowired
    private FavoriteServiceQL favoriteService;

    public List<Favorite> getFavorites(int count){
        return favoriteService.getAllFavorites(count);
    }

    public Optional<Favorite> getFavorite(Long id){
        return favoriteService.getFavorite(id);
    }


}
