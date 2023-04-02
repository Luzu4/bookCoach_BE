package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }


    public Game getById(long id){
        return gameRepository.getGameById(id);
    }

//    public List<Game> getGamesByUserId(long id){
//        return gameRepository.getGamesByUserId();
//    }
}
