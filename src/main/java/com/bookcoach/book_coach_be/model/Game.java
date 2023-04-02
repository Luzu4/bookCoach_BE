package com.bookcoach.book_coach_be.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="games")
public class Game {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        }
