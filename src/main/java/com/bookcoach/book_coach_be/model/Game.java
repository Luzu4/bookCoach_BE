package com.bookcoach.book_coach_be.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE book_coach.games SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Game need to have name")
    private String name;
    @NotBlank(message = "Game need to have description")
    private String description;
    @NotBlank(message = "Game need to have image")
    private String imageUrl;
    private String shortGameName;
    private boolean deleted = Boolean.FALSE;
}
