package com.bookcoach.book_coach_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="happy_students")
public class HappyStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String imageUrl;
}
