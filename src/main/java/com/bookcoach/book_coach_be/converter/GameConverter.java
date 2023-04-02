package com.bookcoach.book_coach_be.converter;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class GameConverter implements Converter<String, Game> {

    @Autowired
    private GameService gameService;

    @Override
    public Game convert(String s){
        return gameService.getById(Long.parseLong(s));
    }
}
