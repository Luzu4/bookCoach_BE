package com.bookcoach.book_coach_be.service;

import com.bookcoach.book_coach_be.model.HappyStudent;
import com.bookcoach.book_coach_be.repository.HappyStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HappyStudentService {

    private final HappyStudentRepository happyStudentRepository;

    public List<HappyStudent> getAllHappyStudents(){
        return happyStudentRepository.findAll();
    }
}
