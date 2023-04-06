package com.bookcoach.book_coach_be.controller;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {


    private final LessonService lessonService;

    @GetMapping("/all")
    List<Lesson> getAll(){
        return lessonService.getAll();
    }

    @PostMapping("/save")
    Lesson newLesson(@RequestBody Lesson lesson){
        System.out.println(lesson.toString());
        return lessonService.saveLesson(lesson);
    }

    @PostMapping("/add/player")
    void addPlayerToLesson(@RequestBody Map<String, String> json){
        long lessonId= Long.parseLong(json.get("lessonId"));
        lessonService.addPlayerToLesson(json.get("playerEmail"), lessonId);
    }

    @GetMapping("/game/{id}/user/{userId}")
    List<Lesson> getLessonsByGameId(@PathVariable("id") long id, @PathVariable("userId") long userId ){
        return lessonService.getLessonsByGameIdAndUserId(id,userId);
    }

    @GetMapping("/free/game/{gameId}/user/{userId}")
    List<Lesson> getLessonsByGameIdAndUserIdWhereEmailIsNull(@PathVariable("gameId") long gameId, @PathVariable("userId") long userId ){
        return lessonService.getLessonsByGameIdAndUserIdWhereEmailIsNull(gameId,userId);
    }

}
