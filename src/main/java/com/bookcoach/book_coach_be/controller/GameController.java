package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.service.GameService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/all")
    List<Game> all(){
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    Game getById(@PathVariable("id") Game game){
        return game;
    }

    @GetMapping("/user/{id}")
    List<Game> getGamesByUserId(@PathVariable("id") long id){
        return gameService.getGamesByUserId(id);
    }

    @GetMapping("/user")
    List<Game> getGamesByUserEmail(@AuthenticationPrincipal User user){
        return gameService.getGamesByUserId(user.getId());
    }



}
