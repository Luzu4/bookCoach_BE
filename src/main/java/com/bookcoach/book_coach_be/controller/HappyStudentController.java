package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.model.HappyStudent;
import com.bookcoach.book_coach_be.service.HappyStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/happy")
@RequiredArgsConstructor
public class HappyStudentController {

    private final HappyStudentService happyStudentService;

    @GetMapping
    List<HappyStudent> all(){
        return happyStudentService.getAllHappyStudents();
    }
}
