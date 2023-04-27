package com.bookcoach.book_coach_be.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE book_coach.games SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name="games")
public class Game {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String description;
        private String imageUrl;
        private String shortGameName;
        private boolean deleted = Boolean.FALSE;
        }
