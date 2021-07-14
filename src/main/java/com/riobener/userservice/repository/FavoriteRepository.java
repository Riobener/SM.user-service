package com.riobener.userservice.repository;

import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

}
