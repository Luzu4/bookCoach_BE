package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUserByType(String type){
        return userRepository.getUsersByType(type);
    }
}
