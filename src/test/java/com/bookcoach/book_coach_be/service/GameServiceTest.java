package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;


    @Test
    void getAllGames_should_return_allGames_list() {
        //Given
        Game game = this.buildTestingGame();

        //When
        when(gameRepository.findAll()).thenReturn(List.of(game));
        List<Game> games = this.gameService.getAllGames();

        //Then
        assertEquals(1, games.size());
        verify(this.gameRepository).findAll();
    }

    @Test
    void getById_should_return_game() {
        //Given
        Game game = this.buildTestingGame();

        //When
        when(gameRepository.getGameById(1L)).thenReturn(game);
        Game returnedGame = this.gameService.getById(1L);

        //Then
        assertEquals(game.getId(), returnedGame.getId());
        assertEquals("game", returnedGame.getName());
        verify(this.gameRepository).getGameById(1L);

    }

    @Test
    void getGamesByUserId() {
    }

    @Test
    void removeGameById_should_delete_game() {
        //When
        this.gameService.removeGameById(1L);

        //Then
        verify(this.gameRepository).deleteById(1L);
    }

    @Test
    void editGameById_should_return_ok() {
        //Given
        Game game = this.buildTestingGame();
        gameRepository.save(game);
        game.setName("updated name");
        //wWhen
        ResponseEntity<?> response = gameService.editGameById(game);
        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void getGameByName_should_return_game() {
        //Given
        Game game = this.buildTestingGame();

        //When
        when(gameRepository.findGameByName("game")).thenReturn(Optional.of(game));
        Optional<Game> returnedGame = this.gameService.getGameByName("game");

        //Then
        assertEquals(game.getId(), returnedGame.get().getId());
        verify(this.gameRepository).findGameByName("game");
    }

    @Test
    void addNewGame_should_insert_new_game() {
        //Given
        Game game = this.buildTestingGame();

        //When
        this.gameService.addNewGame(game);

        //Then
        verify(this.gameRepository).save(game);
    }

    @Test
    void addNewGame_should_not_add_game_with_existing_name(){
        //Give
        Game game = this.buildTestingGame();

        //When
        when(gameRepository.findGameByName(game.getName())).thenReturn(Optional.of(game));

        //Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> gameService.addNewGame(game));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Game already exists!", exception.getReason());

    }
    @Test
    void addNewGame_should_not_add_game_with_empty_name(){
        //Given
        Game game = new Game();
        game.setName("");

        //When

        //Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->gameService.addNewGame(game));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Game need to have name", exception.getReason());

    }
    @Test
    void addNewGame_should_not_add_game_with_short_name(){
        //Given
        Game game = new Game();
        game.setName("a");

        //When

        //Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->gameService.addNewGame(game));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Game need to have name", exception.getReason());

    }
    @Test
    void addNewGame_should_not_add_game_with_null_name(){
        //Given
        Game game = new Game();

        //When

        //Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->gameService.addNewGame(game));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Game need to have name", exception.getReason());

    }



    private Game buildTestingGame(){
        Game game = new Game();
        game.setName("game");
        game.setId(1L);
        game.setShortGameName("ga");
        game.setDescription("desc");
        game.setDeleted(false);
        game.setImageUrl("imageURL");
        return game;
    }


}