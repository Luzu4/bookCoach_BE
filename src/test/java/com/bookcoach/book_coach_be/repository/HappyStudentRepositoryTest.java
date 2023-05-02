package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.HappyStudent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HappyStudentRepositoryTest {
    @Autowired
    private HappyStudentRepository happyStudentRepository;


    @Test
    void findAll_should_return_happy_student_list(){
        //When
        List<HappyStudent> happyStudents = this.happyStudentRepository.findAll();

        //Then
        assertEquals(5,happyStudents.size());
    }

    @Test
    void findById_should_return_happyStudent(){
        //When
        Optional<HappyStudent> happyStudent = happyStudentRepository.findById(2L);

        //Then
        assertTrue(happyStudent.isPresent());
    }
}