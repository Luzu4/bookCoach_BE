package com.bookcoach.book_coach_be.controller;

import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/type/{type}")
    List<User> getUsersByType(@PathVariable("type") String type){

        return userService.getUserByType(Role.valueOf(type.toUpperCase()));
    }
}
