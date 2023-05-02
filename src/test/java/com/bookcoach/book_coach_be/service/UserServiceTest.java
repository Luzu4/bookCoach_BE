package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.dto.EditUserDataDTO;
import com.bookcoach.book_coach_be.dto.EditUserRoleGamesDTO;
import com.bookcoach.book_coach_be.dto.UserDTO;
import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.model.UserDetailsAll;
import com.bookcoach.book_coach_be.repository.UserDetailsAllRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDetailsAllRepository userDetailsAllRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void getUserByType_should_return_list_of_users() {
        //Given
        User user = buildTestingUser();
        //When
        when(userRepository.getUsersByRole(Role.COACH)).thenReturn(List.of(user));
        List<UserDTO> users = this.userService.getUserByType(Role.COACH);

        //Then
        assertEquals(1,users.size());
        verify(this.userRepository).getUsersByRole(Role.COACH);

    }

    @Test
    void getUserByEmail_should_return_user() {
        //Given
        User user = buildTestingUser();
        //When
        when(userRepository.findByEmail("coach@gmail.com")).thenReturn(Optional.of(user));
        Optional<UserDTO> returnedUser = this.userService.getUserByEmail("coach@gmail.com");

        //Then
        assertEquals(user.getId(), returnedUser.get().getId());
        verify(this.userRepository).findByEmail("coach@gmail.com");
    }

    @Test
    void getAllCoachesByGame_should_return_list_of_users() {
        //Given
        User user = buildTestingUser();

        //When
        when(userRepository.getAllCoachesByGame(1L)).thenReturn(List.of(user));
        List<User> users = this.userService.getAllCoachesByGame(1L);

        //Then
        assertEquals(1, users.size());
        verify(this.userRepository).getAllCoachesByGame(1L);

    }

    @Test
    void getAllUsers_should_return_list_of_users() {
        //Given
        User user = buildTestingUser();

        //When
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<UserDTO> userList = this.userService.getAllUsers();

        //Then
        assertEquals(1, userList.size());
        verify(this.userRepository).findAll();
    }

    @Test
    void updateUserGamesAndRole_should_return_status_ok() {
        //Given
        User user = this.buildTestingUser();
        EditUserRoleGamesDTO editUserRoleGamesDTO = new EditUserRoleGamesDTO();
        editUserRoleGamesDTO.setRole(Role.ADMIN);
        editUserRoleGamesDTO.setUserId(1L);
        editUserRoleGamesDTO.setGamesId(new Long[]{1L});

        //When
        when(userRepository.getById(1L)).thenReturn(user);
        System.out.println("userRepository.getById(1L) = " + userRepository.getById(1L));
        ResponseEntity<?> response = this.userService.updateUserGamesAndRole(editUserRoleGamesDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //Then
        verify(userRepository).updateUserRole(Role.ADMIN, 1L);
    }

    @Test
    void updateUserData_should_update_userData() {
        //Given
        User user = buildTestingUser();
        EditUserDataDTO editUserDataDTO = buildTestingEditUserDTO();
        userRepository.save(user);

        //When
        ResponseEntity<?> response = userService.updateUserData(editUserDataDTO,user);

        //Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

    }
    private UserDetailsAll buildTestingUserDetailsAll(){
        UserDetailsAll userDetailsAll = new UserDetailsAll();
        userDetailsAll.setId(1L);
        userDetailsAll.setDescription("desc");
        userDetailsAll.setCity("testCity");
        userDetailsAll.setImageUrl("imageULR");
        userDetailsAll.setCountry("testCountry");
        userDetailsAll.setLanguage("testLanguage");
        userDetailsAll.setGame(List.of(buildTestingGame()));
        return userDetailsAll;
    };
    private User buildTestingUser(){
        User user = new User();
        user.setEmail("coach@gmail.com");
        user.setId(1);
        user.setRole(Role.COACH);
        user.setNickName("coach");
        user.setUserDetails(buildTestingUserDetailsAll());
        return user;
    }

    private Game buildTestingGame(){
        Game game = new Game();
        game.setName("game");
        game.setId(1L);
        game.setShortGameName("ga");
        game.setDescription("desc");
        game.setDeleted(false);
        game.setImageUrl("imageURL");
        return game;
    }

    private EditUserDataDTO buildTestingEditUserDTO(){
        EditUserDataDTO editUserDataDTO = new EditUserDataDTO();
        editUserDataDTO.setCity("testCityUpdated");
        editUserDataDTO.setCountry("testCountryUpdated");
        editUserDataDTO.setLanguage("testLanguageUpdated");
        editUserDataDTO.setDescription("descUpdated");
        editUserDataDTO.setEmail("updated@gmail.com");
        editUserDataDTO.setImageUrl("imageULRUpdated");
        editUserDataDTO.setNickName("coachUpdated");
        editUserDataDTO.setPassword("");
        return editUserDataDTO;
    }

}