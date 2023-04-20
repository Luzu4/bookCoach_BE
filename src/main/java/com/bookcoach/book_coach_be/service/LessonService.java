package com.bookcoach.book_coach_be.service;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.repository.LessonRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Transactional
    public void addPlayerToLesson(String playerEmail, long lessonId) {
        lessonRepository.addPlayerToLesson(playerEmail, lessonId);
    }

    public List<Lesson> getLessonsByGameIdAndUserId(long id, long userId) {
        return lessonRepository.getLessonsByGameIdAndUserId(id, userId);
    }

    public List<Lesson> getLessonsByGameIdAndUserIdWhereEmailIsNull(long gameId, long userId) {
        return lessonRepository.findLessonsByGameIdAndUserIdWhereEmailIsNull(gameId, userId);
    }

    public List<Lesson> getLessonsByPlayerId(long playerId) {
        return lessonRepository.getLessonsByPlayerEmail(userRepository.getById(playerId).getEmail());
    }

    public List<Lesson> getLessonsByCoachId(long coachId) {
        return lessonRepository.getLessonsByUserId(coachId);
    }

    @Transactional
    public void removeLessonById(long lessonId, long userId) {
        if (lessonRepository.getLessonById(lessonId).getUser().getId() == userId) {
            System.out.println("WE ARE HERE");
            System.out.println(lessonId);
            lessonRepository.removeLessonById(lessonId);
        }
    }

    @Transactional
    public void removePlayerFromLessonById(long lessonId, String userEmail){
        Lesson lessonToEdit = lessonRepository.getLessonById(lessonId);
        System.out.println("lessonToEdit = " + lessonToEdit);
        if(lessonToEdit.getPlayerEmail() != null){
            System.out.println("lessonToEdit.getPlayerId() = " + lessonToEdit.getPlayerId());
            if(lessonToEdit.getPlayerEmail().equals(userEmail)){
                System.out.println("lessonToEdit.getPlayerId() == userId = " + (lessonToEdit.getPlayerEmail().equals(userEmail)));
                lessonRepository.removePlayerFromLesson(lessonId);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not student in this lesson");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not student in this lesson");
        }

    }

    public List<Lesson> getLessonsByUserIdAndDate(long userId, LocalDate date){
        return lessonRepository.getLessonsByUserIdAndDate(userId,date);
    }

    @Transactional
    public void addNewLessons(String date, long userid, String gameId, String hours){
        String[] hoursArray = hours.split(",");
        for (String hour : hoursArray) {
            System.out.println("userid = " + userid);
            System.out.println("gameId = " + gameId);
            System.out.println("hour = " + hour);
            System.out.println("date = " + date);

            lessonRepository.addNewLesson(LocalDate.parse(date),userid,Long.parseLong(gameId), LocalTime.parse(hour));
        }



    }


}
