package com.bookcoach.book_coach_be.controller;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {


    private final LessonService lessonService;

    @PostMapping("/save")
    Lesson newLesson(@RequestBody Lesson lesson){
        System.out.println(lesson.toString());
        return lessonService.saveLesson(lesson);
    }
}
