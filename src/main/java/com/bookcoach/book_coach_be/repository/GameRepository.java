package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game getGameById(Long id);

    @Query(nativeQuery = true, value = "SELECT g.id,g.name\n" +
            "FROM games g\n" +
            "inner join user_details_game udg on g.id=udg.game_id\n" +
            "inner join user_details ud on udg.user_details_id = ud.id\n" +
            "inner join users u on ud.id = u.details_id\n" +
            "where u.id = :id")
    List<Game> getGamesByUserId(@Param("id") long id);
}
