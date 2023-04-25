package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game getGameById(Long id);

    @Query(nativeQuery = true, value = "SELECT g.id,g.name,g.description,g.image_url,g.short_game_name \n" +
            "FROM games g\n" +
            "inner join user_details_all_game udg on g.id=udg.game_id\n" +
            "inner join user_details_all ud on udg.user_details_all_id = ud.id\n" +
            "inner join _user u on ud.id = u.user_details_all_id\n" +
            "where u.id = :id")
    List<Game> getGamesByUserId(@Param("id") long id);

    @Modifying
    @Query("UPDATE Game g set g.imageUrl=:imageUrl,g.description=:description, g.name=:name, g.shortGameName=:shortGameName where g.id=:gameId")
    void editGameById(@Param("imageUrl") String imageUrl, @Param("description")String description, @Param("name")String name, @Param("shortGameName") String shortGameName, @Param("gameId") Long gameId);
}
