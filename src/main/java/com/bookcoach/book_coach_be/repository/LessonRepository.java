package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
