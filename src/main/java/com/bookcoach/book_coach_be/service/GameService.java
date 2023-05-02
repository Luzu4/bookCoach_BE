package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public Optional<Game> getGameByName(String name){
        return gameRepository.findGameByName(name);
    }
    public Game addNewGame(Game game){
        if (game.getName() != null) {
            if(game.getName().length()>1){
                if(getGameByName(game.getName()).isEmpty()){
                    try{
                        return gameRepository.save(game);
                    }catch(Exception e){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Smth went wrong");
                    }
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game already exists!");
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game need to have name");






    }
}
