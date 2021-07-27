package com.riobener.userservice.application.controllers;

import com.riobener.userservice.domain.model.entities.Favorite;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.EmptyUserFavoritesException;
import com.riobener.userservice.domain.model.exceptions.EmptyUserListException;
import com.riobener.userservice.domain.model.exceptions.FavoriteNotFoundException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;
import com.riobener.userservice.infrastructure.dto.request.CreateFavoriteDto;
import com.riobener.userservice.infrastructure.dto.response.FavoriteResponseDto;
import com.riobener.userservice.infrastructure.dto.response.UserResponseDto;
import com.riobener.userservice.infrastructure.exceptions.RestFileNotFoundException;
import com.riobener.userservice.infrastructure.services.FavoriteService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/favorite/create")
    @ApiOperation(value = "Создает избранное из идентификаторов сэмпла и пользователя", response = FavoriteResponseDto.class)
    public ResponseEntity createFavorite(@RequestBody CreateFavoriteDto dto) {
        try {
            FavoriteResponseDto favoriteResponseDto = modelMapper.map(favoriteService.createFavorite(dto),
                    FavoriteResponseDto.class);
            return ResponseEntity.ok(favoriteResponseDto);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RestFileNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/favorite/{userId}")
    @ApiOperation(value = "Возвращает все избранные элементы пользователя", response = FavoriteResponseDto.class)
    public ResponseEntity getAllFavoritesByUser(@PathVariable Long userId) {
        try {
            Iterable<Favorite> favorites = favoriteService.findAllFavoritesByUser(userId);
            return ResponseEntity.ok(StreamSupport.stream(favorites.spliterator(), false).
                    map(favorite -> modelMapper.map(favorite, FavoriteResponseDto.class)).collect(Collectors.toList()));
        } catch (EmptyUserFavoritesException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @DeleteMapping("/favorite/delete/{id}")
    @ApiOperation(value = "Удаляет избранное по идентификатору", response = FavoriteResponseDto.class)
    public ResponseEntity deleteFavorite(@PathVariable Long id) {
        try {
            FavoriteResponseDto favoriteResponseDto = modelMapper.map(favoriteService.deleteFavorite(id),
                    FavoriteResponseDto.class);
            return ResponseEntity.ok(favoriteResponseDto);
        } catch (FavoriteNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @DeleteMapping("/favorite/delete/")
    @ApiOperation(value = "Удаляет все элементы из списка избранных по идентификатору пользователя",
            response = FavoriteResponseDto.class)
    public ResponseEntity deleteAllFavoritesByUserId(@RequestParam Long userId) {
        try {
            Iterable<Favorite> favorites = favoriteService.deleteAllFavoritesByUserId(userId);
            return ResponseEntity.ok(StreamSupport.stream(favorites.spliterator(), false).
                    map(favorite -> modelMapper.map(favorite, FavoriteResponseDto.class)).collect(Collectors.toList()));

        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (EmptyUserFavoritesException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
