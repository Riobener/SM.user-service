package com.riobener.userservice.controller;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;
import com.riobener.userservice.infrastructure.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/authenticated")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/favorite/create")
    public ResponseEntity createFavorite(@RequestBody Favorite favorite){
        try{
            return ResponseEntity.ok(favoriteService.createFavorite(favorite));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/favorite/delete/{id}")
    public ResponseEntity deleteFavorite(@PathVariable Long id){
        try{
            return ResponseEntity.ok(favoriteService.deleteFavorite(id));
        }catch(FavoriteNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
