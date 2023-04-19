package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUserByType(Role type){
        return userRepository.getUsersByRole(type);
    }

    public User getById(long id){
        return userRepository.getById(id);
    }

    public List<User> getAllCoachesByGame(long gameId){
        return userRepository.getAllCoachesByGame(gameId);
    }


}
