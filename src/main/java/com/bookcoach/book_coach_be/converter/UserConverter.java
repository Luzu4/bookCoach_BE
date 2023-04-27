//package com.bookcoach.book_coach_be.converter;
//
//import com.bookcoach.book_coach_be.dto.CoachDTO;
//import com.bookcoach.book_coach_be.model.User;
//import com.bookcoach.book_coach_be.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserConverter implements Converter<String, User> {
//
//    @Autowired
//    private UserService userService;
//
//
//    @Override
//    public User convert(String source) {
//        return userService.getById(Integer.parseInt(source));
//    }
//
//
//}
