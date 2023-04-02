package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
