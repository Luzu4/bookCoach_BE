package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.service.GameService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class GameController {

    private final GameService gameService;

    @GetMapping("/all")
    List<Game> all(){
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    Game getById(@PathVariable("id") Game game){
//        System.out.println("user.getAttributes() = " + user.getAttributes());
//        System.out.println("user.getAttribute(\"email\") = " + user.getAttribute("email"));
        return game;
    }

    @GetMapping("/user/{id}")
    List<Game> getGamesByUserId(@PathVariable("id") long id){
        System.out.println(id);
        return gameService.getGamesByUserId(id);
    }
}
