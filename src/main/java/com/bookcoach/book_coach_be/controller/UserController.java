package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.dto.UserDTO;
import com.bookcoach.book_coach_be.dto.EditUserDataDTO;
import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/type/{type}")
    List<UserDTO> getUsersByType(@PathVariable("type") String type){

        return userService.getUserByType(Role.valueOf(type.toUpperCase()));
    }

    @GetMapping("/coaches/game/{gameId}")
    List<User> getCoachesByGameId(@PathVariable("gameId") Long gameId){
        return userService.getAllCoachesByGame(gameId);
    }

    @GetMapping("/all")
    List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping("/admin/edit")
    ResponseEntity<?> updateUserRoleAndGames(@RequestBody EditUserRoleGamesDTO editUserRoleGamesDTO){
        return ResponseEntity.ok(userService.updateUserGamesAndRole(editUserRoleGamesDTO));
    }

    @GetMapping("/{userEmail}")
    Optional<UserDTO> getUserById(@PathVariable("userEmail") String userEmail, @AuthenticationPrincipal User user){
        return userService.getUserByEmail(userEmail);
    }

    @PatchMapping("/edit")
    ResponseEntity<?> updateUserData(@RequestBody EditUserDataDTO editUserDataDTO, @AuthenticationPrincipal User user){
        return userService.updateUserData(editUserDataDTO,user);
    }

}