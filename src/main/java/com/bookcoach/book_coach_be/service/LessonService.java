package com.bookcoach.book_coach_be.service;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.repository.LessonRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> getAll(){
        return lessonRepository.findAll();
    }

    public Lesson saveLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    @Transactional
    public void addPlayerToLesson(String playerEmail, long lessonId){
        lessonRepository.addPlayerToLesson(playerEmail, lessonId);
    }

    public List<Lesson> getLessonsByGameIdAndUserId(long id, long userId){
        return lessonRepository.getLessonsByGameIdAndUserId(id, userId);
    }
    public List<Lesson> getLessonsByGameIdAndUserIdWhereEmailIsNull(long gameId, long userId){
        return lessonRepository.findLessonsByGameIdAndUserIdWhereEmailIsNull(gameId, userId);
    }




}
