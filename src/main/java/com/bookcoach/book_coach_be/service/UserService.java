package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.dto.UserDTO;
import com.bookcoach.book_coach_be.dto.EditUserDataDTO;
import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.repository.LessonRepository;
import com.bookcoach.book_coach_be.repository.UserDetailsAllRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsAllRepository userDetailsAllRepository;
    private final PasswordEncoder passwordEncoder;
    private final LessonRepository lessonRepository;

    public List<UserDTO> getUserByType(Role type) {
        List<UserDTO> userDTOList = new ArrayList<>();
        userRepository.getUsersByRole(type).forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setNickName(user.getNickName());
            userDTO.setRole(user.getRole());
            userDTO.setEmail(user.getEmail());
            userDTO.setId(user.getId());
            userDTO.setUserDetailsAll(user.getUserDetails());
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    public Optional<UserDTO> getUserByEmail(String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        return optionalUser.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setNickName(user.getNickName());
            userDTO.setRole(user.getRole());
            userDTO.setEmail(user.getEmail());
            userDTO.setId(user.getId());
            userDTO.setUserDetailsAll(user.getUserDetails());
            return userDTO;
        });
    }

    public List<User> getAllCoachesByGame(long gameId) {
        return userRepository.getAllCoachesByGame(gameId);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setNickName(user.getNickName());
            userDTO.setRole(user.getRole());
            userDTO.setEmail(user.getEmail());
            userDTO.setId(user.getId());
            userDTO.setUserDetailsAll(user.getUserDetails());
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Transactional
    public ResponseEntity<String> updateUserGamesAndRole(EditUserRoleGamesDTO editUserRoleGamesDTO) {

        userRepository.updateUserRole(editUserRoleGamesDTO.getRole(), editUserRoleGamesDTO.getUserId());

        long userDetailsId = userRepository.getById(editUserRoleGamesDTO.getUserId()).getUserDetails().getId();

        userRepository.removeAllUserGames(userDetailsId);
        for (Long gameId : editUserRoleGamesDTO.getGamesId()) {
            userRepository.updateUserGames(gameId, userDetailsId);
        }
        return ResponseEntity.ok("Updated");
    }


    @Transactional
    public ResponseEntity<?> updateUserData(EditUserDataDTO editUserDataDTO, User user) {
        Long userId = Long.valueOf(user.getId());
        if (!user.getEmail().equals(editUserDataDTO.getEmail()) && userRepository.findByEmail(editUserDataDTO.getEmail()).isPresent()) {
            System.out.println(editUserDataDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Email is Already in use");
        }
        if (!editUserDataDTO.getPassword().isEmpty()) {
            userRepository.updateUserPassword(passwordEncoder.encode(editUserDataDTO.getPassword()), userId);
        }
        userRepository.updateUserEmail(editUserDataDTO.getEmail(), userId);
        lessonRepository.editPlayerEmail(editUserDataDTO.getEmail(), user.getEmail());
        userRepository.updateUserNickName(editUserDataDTO.getNickName(), userId);
        userDetailsAllRepository.updateUserDetailsAllData(
                editUserDataDTO.getCity(),
                editUserDataDTO.getCountry(),
                editUserDataDTO.getLanguage(),
                editUserDataDTO.getDescription(),
                editUserDataDTO.getImageUrl(),
                user.getUserDetails().getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("UPDATED");


    }

}
