package com.riobener.userservice.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.FavoriteNotFoundException;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.repository.UserRepository;
import com.riobener.userservice.service.FavoriteServiceQL;
import com.riobener.userservice.service.UserServiceQL;
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
