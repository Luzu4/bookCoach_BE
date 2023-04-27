package com.bookcoach.book_coach_be.service;
import com.bookcoach.book_coach_be.model.HappyStudent;
import com.bookcoach.book_coach_be.repository.HappyStudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class HappyStudentServiceTest {

    @Mock
    private HappyStudentRepository happyStudentRepository;

    @InjectMocks
    private HappyStudentService happyStudentService;

    private HappyStudent buildTestingHappyStudent(){
        HappyStudent happyStudent = new HappyStudent();
        happyStudent.setId(1);
        happyStudent.setImageUrl("IMAGE_URL");
        happyStudent.setDescription("DESCRIPTION");
        happyStudent.setName("NAME");
        return happyStudent;
    }


    @Test
    void should_throw_nullPointerException() {
        assertThrows(NullPointerException.class, () -> happyStudentService.getAllHappyStudents());
    }

    @Test
    void findAll_shoud_return_happyStudents_list(){
        // Given
        HappyStudent happyStudent = this.buildTestingHappyStudent();

        // When
        when(happyStudentRepository.findAll()).thenReturn(List.of(happyStudent));
        List<HappyStudent> happyStudents = this.happyStudentService.getAllHappyStudents();

        // Then
        assertEquals(1, happyStudents.size());
        verify(this.happyStudentRepository.findAll());

    }

}