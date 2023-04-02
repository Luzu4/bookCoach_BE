package com.bookcoach.book_coach_be.service;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Lesson saveLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

}
