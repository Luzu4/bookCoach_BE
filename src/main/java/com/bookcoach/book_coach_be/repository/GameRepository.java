package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game getGameById(Long id);

//    @Query("" )
//    List<Game> getGamesByUserId();
}
