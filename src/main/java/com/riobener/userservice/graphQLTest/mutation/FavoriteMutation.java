/*
package com.riobener.userservice.graphQLTest.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMutation implements GraphQLMutationResolver {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteServiceQL favoriteServiceQL;

    public Favorite createFavorite(Long sampleId, Long userId) {
        User user = userRepository.findById(userId).get();
        return favoriteServiceQL.createFavorite(sampleId,user);
    }
    public Favorite deleteFavorite(Long id) throws FavoriteNotFoundException {
        return favoriteServiceQL.deleteFavoriteById(id);
    }
}
*/
