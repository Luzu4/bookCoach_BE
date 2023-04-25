package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.HappyStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HappyStudentRepository extends JpaRepository<HappyStudent,Long> {
}
