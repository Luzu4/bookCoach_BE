package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.*;
import com.bookcoach.book_coach_be.repository.LessonRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LessonService lessonService;



    @Test
    void getAll_should_return_lesson_list() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.findAll()).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getAll();

        //Then
        assertEquals(1,lessonList.size());
        verify(lessonRepository).findAll();
    }

    @Test
    void saveLesson_should_insert_and_return_new_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        Lesson addedLesson = lessonService.saveLesson(lesson);

        //Then
        assertEquals(addedLesson.getId(),1L);
        verify(lessonRepository).save(lesson);

    }

    @Test
    void addPlayerToLesson_should_add_player_to_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();
        String playerEmail = "player123@gmail.com";

        //When
        ResponseEntity<?> response = lessonService.addPlayerToLesson(playerEmail,lesson.getId());
        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());

    }

    @Test
    void getLessonsByGameIdAndUserId_should_return_lesson_list() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.getLessonsByGameIdAndUserId(1L,1L)).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getLessonsByGameIdAndUserId(1L,1L);

        //Then
        assertEquals(1, lessonList.size());
        verify(lessonRepository).getLessonsByGameIdAndUserId(1L,1L);
    }

    @Test
    void getLessonsByGameIdAndUserIdWhereEmailIsNull_should_return_lesson_list() {
        //Given
        Lesson lesson = buildTestingLesson();
        lesson.setPlayerEmail(null);

        //When
        when(lessonRepository.findLessonsByGameIdAndUserIdWhereEmailIsNull(1L,1L)).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getLessonsByGameIdAndUserIdWhereEmailIsNull(1L,1L);

        //Then
        assertEquals(1, lessonList.size());
        verify(lessonRepository).findLessonsByGameIdAndUserIdWhereEmailIsNull(1L,1L);
    }

    @Test
    void getLessonsByPlayerId_should_return_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();
        User userAddedToLesson = new User();
        userAddedToLesson.setId(1);
        userAddedToLesson.setEmail("player@gmail.com");

        //When
        when(userRepository.getById(1L)).thenReturn(userAddedToLesson);
        when(lessonRepository.getLessonsByPlayerEmail("player@gmail.com")).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getLessonsByPlayerId(1L);

        //Then
        assertEquals(1,lessonList.size());
        verify(lessonRepository).getLessonsByPlayerEmail("player@gmail.com");
    }

    @Test
    void getLessonsByCoachId_should_return_lesson_list() {
        //Give
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.getLessonsByUserId(1L)).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getLessonsByCoachId(1L);

        //Then
        assertEquals(1,lessonList.size());
        verify(lessonRepository).getLessonsByUserId(1L);
    }

    @Test
    void removeLessonById_should_delete_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.getLessonById(1L)).thenReturn(lesson);
        lessonService.removeLessonById(1,buildTestingUser());
        //Then
        verify(lessonRepository).getLessonById(1L);
    }

    @Test
    void removePlayerFromLessonById_should_remove_player_from_lesson_when_coach() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.getLessonById(1L)).thenReturn(lesson);
        lessonService.removePlayerFromLessonById(1L,buildTestingUser());

        //Then
        verify(lessonRepository).removePlayerFromLesson(1L);

    }
    @Test
    void removePlayerFromLessonById_should_throw_exception_student_not_in_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();
        User player = buildTestingUser();
        player.setRole(Role.PLAYER);

        //When
        when(lessonRepository.getLessonById(1L)).thenReturn(lesson);

        //Then
        assertThrows(ResponseStatusException.class, ()->{lessonService.removePlayerFromLessonById(1L,player);});

    }

    @Test
    void removePLayerFromLessonById_should_throw_exception_when_no_student_assigned(){
        //Given
        Lesson lesson = buildTestingLesson();
        lesson.setPlayerEmail(null);
        lesson.setPlayerId(null);

        //when
        when(lessonRepository.getLessonById(1L)).thenReturn(lesson);

        //then
        assertThrows(ResponseStatusException.class, ()->{
            lessonService.removePlayerFromLessonById(1L, buildTestingUser());
        });

    }
    @Test
    void removePlayerFromLessonById_should_remove_player_from_lesson_when_player(){
        //Given
        Lesson lesson = buildTestingLesson();
        User player = buildTestingUser();
        player.setEmail(lesson.getPlayerEmail());
        player.setRole(Role.PLAYER);

        //When
        when(lessonRepository.getLessonById(1L)).thenReturn(lesson);
        lessonService.removePlayerFromLessonById(1L,player);

        //Then
        verify(lessonRepository).removePlayerFromLesson(1L);
    }

    @Test
    void getLessonsByUserIdAndDate_should_return_lesson_list() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        when(lessonRepository.getLessonsByUserIdAndDate(1L,LocalDate.of(1994,4,18))).thenReturn(List.of(lesson));
        List<Lesson> lessonList = lessonService.getLessonsByUserIdAndDate(1L, LocalDate.of(1994,4,18));
        //Then
        assertEquals(1, lessonList.size());
        verify(lessonRepository).getLessonsByUserIdAndDate(1L, LocalDate.of(1994,4,18));

    }

    @Test
    void addNewLessons_should_add_new_lesson() {
        //Given
        Lesson lesson = buildTestingLesson();

        //When
        lessonService.addNewLessons(String.valueOf(lesson.getDate()),lesson.getUser().getId(),String.valueOf(lesson.getGame().getId()), String.valueOf(lesson.getTime()));

        //Then
        verify(lessonRepository).addNewLesson(lesson.getDate(),lesson.getUser().getId(),lesson.getGame().getId(), lesson.getTime());

    }

    private Lesson buildTestingLesson(){
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setDate(LocalDate.of(1994,4,18));
        lesson.setGame(buildTestingGame());
        lesson.setDeleted(false);
        lesson.setTime(LocalTime.of(14,44));
        lesson.setUser(buildTestingUser());
        lesson.setPlayerId(2L);
        lesson.setPlayerEmail("player@gmail.com");
        return lesson;
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
}