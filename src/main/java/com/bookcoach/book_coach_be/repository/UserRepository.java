package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u where u.userType = :type")
    List<User> getUsersByType(@Param("type") String type);
}
