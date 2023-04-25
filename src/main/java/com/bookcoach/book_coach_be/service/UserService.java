package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.dto.EditUserDataDTO;
import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.repository.GameRepository;
import com.bookcoach.book_coach_be.repository.UserDetailsAllRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsAllRepository userDetailsAllRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUserByType(Role type){
        return userRepository.getUsersByRole(type);
    }

    public User getById(long id){
        return userRepository.getById(id);
    }

    public Optional<User> getUserByEmail(String userEmail, User user){
        System.out.println(userEmail);
        System.out.println(user.getId());
        System.out.println(user.getUserDetails().getId());
        return userRepository.findByEmail(userEmail);
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


    @Transactional
    public ResponseEntity<?> updateUserData(EditUserDataDTO editUserDataDTO, User user){
        Long userId = Long.valueOf(user.getId());
        if(!user.getEmail().equals(editUserDataDTO.getEmail()) && userRepository.findByEmail(editUserDataDTO.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email is already in use");
        }
        if(!editUserDataDTO.getPassword().isEmpty()){
            userRepository.updateUserPassword(passwordEncoder.encode(editUserDataDTO.getPassword()),userId);
        }
        userRepository.updateUserEmail(editUserDataDTO.getEmail(),userId);
        userRepository.updateUserNickName(editUserDataDTO.getNickName(),userId);
        userDetailsAllRepository.updateUserDetailsAllData(
                editUserDataDTO.getCity(),
                editUserDataDTO.getCountry(),
                editUserDataDTO.getLanguage(),
                editUserDataDTO.getDescription(),
                editUserDataDTO.getImageUrl(),
                user.getUserDetails().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");


    }

}
