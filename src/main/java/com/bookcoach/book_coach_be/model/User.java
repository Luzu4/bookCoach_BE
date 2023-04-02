package com.bookcoach.book_coach_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private long userType;
    private String nickName;

    @OneToOne
    @JoinColumn(name="details_id", unique = true)
    private UserDetails userDetails;

}
