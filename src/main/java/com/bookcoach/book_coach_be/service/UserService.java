package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.repository.GameRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public List<User> getUserByType(Role type){
        return userRepository.getUsersByRole(type);
    }

    public User getById(long id){
        return userRepository.getById(id);
    }

    public List<User> getAllCoachesByGame(long gameId){
        return userRepository.getAllCoachesByGame(gameId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public ResponseEntity<String> updateUserGamesAndRole(EditUserRoleGamesDTO editUserRoleGamesDTO){

        userRepository.updateUserRole(editUserRoleGamesDTO.getRole(),editUserRoleGamesDTO.getUserId());

        long userDetailsId = userRepository.getById(editUserRoleGamesDTO.getUserId()).getUserDetails().getId();

        userRepository.removeAllUserGames(userDetailsId);
        for (Long gameId : editUserRoleGamesDTO.getGamesId()) {
            userRepository.updateUserGames(gameId,userDetailsId);
        }
        return ResponseEntity.ok("Updated");
    }

}
