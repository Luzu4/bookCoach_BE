package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAll();

    List<Lesson> getLessonsByGameIdAndUserId(@Param("id") long id, @Param("userId") long userId);

    List<Lesson> findLessonsByGameIdAndUserIdAndAndPlayerEmailIsNull(Long gameId, Long userId);

    @Modifying
    @Query("update Lesson l set l.playerEmail=:playerEmail where l.id=:lessonId")
    void addPlayerToLesson(@Param("playerEmail") String playerEmail, @Param("lessonId") long lessonId);

    @Modifying
    @Query("update Lesson l set l.playerEmail=:playerEmail, l.playerId=:playerId where l.id=:lessonId")
    void addPlayerToLessonEmailAndId(@Param("playerEmail") String playerEmail, @Param("playerId") long playerId, @Param("lessonId") long lessonId);

    List<Lesson> getLessonsByPlayerEmail(@Param("playerEmail") String playerEmail);

    List<Lesson> getLessonsByUserId(@Param("userId") long userId);

    Lesson getLessonById(@Param("lessonId") long lessonId);


    @Modifying
    @Query("update Lesson l set l.playerEmail=null, l.playerId=null where l.id=:lessonId")
    void removePlayerFromLesson(@Param("lessonId") long lessonId);


    List<Lesson> getLessonsByUserIdAndDate(@Param("userId") long userId, @Param("date") LocalDate date);


    @Modifying
    @Query(nativeQuery = true, value = "insert into lessons (date,user_id,game_id,time, deleted) values (:date, :userId,:gameId,:time, false)")
    void addNewLesson(@Param("date") LocalDate date, @Param("userId") long userId, @Param("gameId") long gameId, @Param("time") LocalTime time);

    @Modifying
    @Query("UPDATE Lesson l set l.playerEmail=:newPlayerEmail where l.playerEmail=:oldPlayerEmail")
    void editPlayerEmail(@Param("newPlayerEmail") String newPlayerEmail, @Param("oldPlayerEmail") String oldPlayerEmail);

}
