package com.bookcoach.book_coach_be.service;
import com.bookcoach.book_coach_be.model.HappyStudent;
import com.bookcoach.book_coach_be.repository.HappyStudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HappyStudentServiceTest {



    @Mock
    private HappyStudentRepository happyStudentRepository;

    @InjectMocks
    private HappyStudentService happyStudentService;


    @Test
    void findAll_should_return_happyStudents_list(){
        // Given
        HappyStudent happyStudent = this.buildTestingHappyStudent();

        // When
        when(happyStudentRepository.findAll()).thenReturn(List.of(happyStudent));
        List<HappyStudent> happyStudents = this.happyStudentService.getAllHappyStudents();

        // Then
        assertEquals(1, happyStudents.size());
        verify(this.happyStudentRepository).findAll();

    }

    private HappyStudent buildTestingHappyStudent(){
        HappyStudent happyStudent = new HappyStudent();
        happyStudent.setId(1L);
        happyStudent.setDescription("desc");
        happyStudent.setName("Maciek");
        happyStudent.setImageUrl("imageURL");
        return happyStudent;
    }

}