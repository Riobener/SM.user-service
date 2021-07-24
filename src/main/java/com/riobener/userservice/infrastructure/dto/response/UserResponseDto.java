package com.riobener.userservice.infrastructure.dto.response;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.value_objects.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

public class UserResponseDto {

    private Long id;

    private String username;

    private UserInfo info;

    private List<FavoriteResponseDto> favorites;


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

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public List<FavoriteResponseDto> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteResponseDto> favorites) {
        this.favorites = favorites;
    }
}
