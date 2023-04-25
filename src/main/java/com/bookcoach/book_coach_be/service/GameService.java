package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Game> getGamesByUserId(long id){
        return gameRepository.getGamesByUserId(id);
    }

    @Transactional
    public ResponseEntity<?> removeGameById(Long gameId){
        gameRepository.deleteById(gameId);
        return ResponseEntity.ok("DONE");
    }

    @Transactional
    public ResponseEntity<?> editGameById(Game game){
        gameRepository.editGameById(game.getImageUrl(), game.getDescription(), game.getName(), game.getShortGameName(), game.getId());
        return ResponseEntity.ok("DONE");
    }

    public Game addNewGame(Game game){
        return gameRepository.save(game);
    }
}
