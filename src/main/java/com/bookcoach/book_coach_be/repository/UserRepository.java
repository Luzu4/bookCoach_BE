package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
