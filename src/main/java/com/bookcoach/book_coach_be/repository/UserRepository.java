package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.Game;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from User u where u.role = :type")
    List<User> getUsersByRole(@Param("type") Role type);

    User getById(Long id);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u " +
            "JOIN u.userDetails uda " +
            "JOIN uda.game udag " +
            "WHERE udag.id = :gameId")
    List<User> getAllCoachesByGame(@Param("gameId") Long gameId);

    @Modifying
    @Query("UPDATE User u set u.role=:newRole where u.id=:userId")
    void updateUserRole(@Param("newRole") Role newRole, @Param("userId") long userId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO book_coach.user_details_all_game values (:userDetailsId, :newGameId)")
    void updateUserGames(@Param("newGameId") Long newGameId, @Param("userDetailsId") long userDetailsId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM book_coach.user_details_all_game where user_details_all_id=:userDetailsId")
    void removeAllUserGames(@Param("userDetailsId") long userDetailsId);

}
