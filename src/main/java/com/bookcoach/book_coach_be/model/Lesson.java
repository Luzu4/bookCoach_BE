package com.bookcoach.book_coach_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String date;
    private String time;
    private String playerNote;
    private long playerId;
    private String playerEmail;

    @ManyToOne
    private Game game;

    @ManyToOne
    private User user;
}
