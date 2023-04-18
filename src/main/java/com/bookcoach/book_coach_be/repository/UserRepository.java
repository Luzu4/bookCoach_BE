package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from User u where u.role = :type")
    List<User> getUsersByRole(@Param("type") Role type);

    User getById(Long id);

    Optional<User> findByEmail(String email);
}
