package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/type/{type}")
    List<User> getUsersByType(@PathVariable("type") String type){

        return userService.getUserByType(Role.valueOf(type.toUpperCase()));
    }

    @GetMapping("/coaches/game/{gameId}")
    List<User> getCoachesByGameId(@PathVariable("gameId") Long gameId){
        return userService.getAllCoachesByGame(gameId);
    }

    @GetMapping("/all")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping("/admin/edit")
    ResponseEntity<?> updateUserRoleAndGames(@RequestBody EditUserRoleGamesDTO editUserRoleGamesDTO){
        return ResponseEntity.ok(userService.updateUserGamesAndRole(editUserRoleGamesDTO));
    }

}

//11 kg- kielbasa i kabanosy ~
//Ogórki 6 kg
//Smalec - 5kg
//Wędzonki szynki - 10 kg ~
//Noga - 5kg