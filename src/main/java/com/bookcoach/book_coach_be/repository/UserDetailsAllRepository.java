package com.bookcoach.book_coach_be.repository;

import com.bookcoach.book_coach_be.model.UserDetailsAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsAllRepository extends JpaRepository<UserDetailsAll, Integer> {

    @Modifying
    @Query("UPDATE UserDetailsAll uda set uda.city=:city, uda.country=:country, uda.language=:language, uda.description=:description, uda.imageUrl=:imageUrl where uda.id=:userDetailsAllId")
    void updateUserDetailsAllData(
            @Param("city") String city,
            @Param("country") String country,
            @Param("language") String language,
            @Param("description") String description,
            @Param("imageUrl") String imageUrl,
            @Param("userDetailsAllId") Long userDetailsAllId);


}
