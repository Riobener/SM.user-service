/*
package com.riobener.userservice.graphQLTest.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.graphQLTest.service.FavoriteServiceQL;
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
*/
