package com.riobener.userservice.model;

import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.value_objects.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserModel {
    private Long id;
    private String username;
    private UserRole userRole;
    private List<FavoriteModel> favorites;

    public static UserModel toModel(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setRole(user.getUserRole());
        if(user.getFavorites()!=null)
        userModel.setFavorites(user.getFavorites().stream().map(FavoriteModel::toModel).collect(Collectors.toList()));
        return userModel;
    }

    private void setRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public static List<UserModel> toListModels(List<User> users){
        List<UserModel> models = new ArrayList<>();
        UserModel userModel;
        for(User user :users){
            userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setUsername(user.getUsername());
            userModel.setRole(user.getUserRole());
            userModel.setFavorites(user.getFavorites().stream().map(FavoriteModel::toModel).collect(Collectors.toList()));
            models.add(userModel);
        }
        return models;
    }
    public UserModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<FavoriteModel> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteModel> favorites) {
        this.favorites = favorites;
    }
}
