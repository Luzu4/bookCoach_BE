package com.bookcoach.book_coach_be.controller;


import com.bookcoach.book_coach_be.model.Lesson;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {


    private final LessonService lessonService;

    @GetMapping("/all")
    List<Lesson> getAll() {
        return lessonService.getAll();
    }

    @PostMapping("/save")
    Lesson newLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @PostMapping("/add/player")
    ResponseEntity<?> addPlayerToLesson(@RequestBody Map<String, String> json) {
        long lessonId = Long.parseLong(json.get("lessonId"));
        return lessonService.addPlayerToLesson(json.get("playerEmail"), lessonId);
    }

    @GetMapping("/game/{id}/user/{userId}")
    List<Lesson> getLessonsByGameId(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        return lessonService.getLessonsByGameIdAndUserId(id, userId);
    }

    @GetMapping("/free/game/{gameId}/user/{userId}")
    List<Lesson> getLessonsByGameIdAndUserIdWhereEmailIsNull(@PathVariable("gameId") long gameId, @PathVariable("userId") long userId) {
        return lessonService.getLessonsByGameIdAndUserIdWhereEmailIsNull(gameId, userId);
    }

    @GetMapping("/all/player")
    List<Lesson> getLessonByPlayerId(@AuthenticationPrincipal User user) {
        return lessonService.getLessonsByPlayerId(user.getId());
    }

    @GetMapping("/all/coach")
    List<Lesson> getLessonByCoachId(@AuthenticationPrincipal User user) {
        return lessonService.getLessonsByCoachId(user.getId());
    }

    @DeleteMapping("/remove/{lessonId}")
    void removeLessonById(@AuthenticationPrincipal User user, @PathVariable("lessonId") long lessonId) {
        lessonService.removeLessonById(lessonId, user);
    }

    @PatchMapping("/remove/player/{lessonId}")
    void removePlayerFromLessonById(@AuthenticationPrincipal User user, @PathVariable("lessonId") long lessonId) {
        lessonService.removePlayerFromLessonById(lessonId, user);
    }

    @GetMapping("/all/coach/date/{date}")
    List<Lesson> getLessonsByUserIdAndDate(@AuthenticationPrincipal User user, @PathVariable("date") LocalDate date) {
        return lessonService.getLessonsByUserIdAndDate(user.getId(), date);
    }

    @PutMapping("/add")
    void addLessons(@AuthenticationPrincipal User user, @RequestBody Map<String, String> json) {
        lessonService.addNewLessons(json.get("date"), user.getId(), json.get("gameId"), json.get("hours"));
    }
}
