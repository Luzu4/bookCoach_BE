package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.service.GameService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("game/all")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    List<Game> all(){
        return gameService.getAllGames();
    }
}
