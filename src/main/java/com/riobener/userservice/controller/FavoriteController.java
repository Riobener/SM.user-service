package com.riobener.userservice.controller;

import com.riobener.userservice.entity.Favorite;
import com.riobener.userservice.exception.FavoriteNotFoundException;
import com.riobener.userservice.exception.UserNotFoundException;
import com.riobener.userservice.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/favorite/create")
    public ResponseEntity createFavorite(@RequestBody Favorite favorite, @RequestParam Long userId){
        try{
            return ResponseEntity.ok(favoriteService.createFavorite(favorite,userId));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/favorite/delete/{id}")
    public ResponseEntity deleteFavorite(@PathVariable Long id){
        try{
            return ResponseEntity.ok(favoriteService.deleteFavoriteById(id));
        }catch(FavoriteNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
