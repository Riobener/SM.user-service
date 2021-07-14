package com.riobener.userservice.model;

import com.riobener.userservice.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserModel {
    private Long id;
    private String username;
    private List<FavoriteModel> favorites;

    public static UserModel toModel(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setFavorites(user.getFavorites().stream().map(FavoriteModel::toModel).collect(Collectors.toList()));
        return userModel;
    }
    public static List<UserModel> toListModels(List<User> users){
        List<UserModel> models = new ArrayList<>();
        UserModel userModel;
        for(User user :users){
            userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setUsername(user.getUsername());
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
