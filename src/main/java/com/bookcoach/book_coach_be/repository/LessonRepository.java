package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAll();

    List<Lesson> getLessonsByGameIdAndUserId(@Param("id") long id, @Param("userId") long userId);
    @Query(nativeQuery = true, value ="select l.* from lessons l\n" +
            "inner join _user u on l.user_id= u.id\n" +
            "inner join games g on l.game_id = g.id\n" +
            "where (u.id=:userId and g.id=:gameId and l.player_email is null );")
    List<Lesson> findLessonsByGameIdAndUserIdWhereEmailIsNull(@Param("userId") long userId, @Param("gameId") long gameId);

    @Modifying
    @Query("update Lesson l set l.playerEmail=:playerEmail where l.id=:lessonId")
    void addPlayerToLesson(@Param("playerEmail") String playerEmail, @Param("lessonId") long lessonId);

    List<Lesson> getLessonsByPlayerEmail(@Param("playerEmail") String playerEmail);

    List<Lesson> getLessonsByUserId(@Param("userId") long userId);

    void removeLessonById(@Param("lessonId") long lessonId);

    Lesson getLessonById(@Param("lessonId") long lessonId);


    @Modifying
    @Query("update Lesson l set l.playerEmail=null, l.playerId=null where l.id=:lessonId")
    void removePlayerFromLesson(@Param("lessonId") long lessonId);
}
