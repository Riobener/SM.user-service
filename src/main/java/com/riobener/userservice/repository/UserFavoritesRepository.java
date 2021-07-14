package com.riobener.userservice.repository;

import com.riobener.userservice.entity.UserFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoritesRepository extends JpaRepository<UserFavorites,Long> {

}
