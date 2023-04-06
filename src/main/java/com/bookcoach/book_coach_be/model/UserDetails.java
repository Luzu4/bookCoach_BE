package com.bookcoach.book_coach_be.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String language;
    private String country;
    private String city;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    (fetch = FetchType.EAGER)
//    @JoinTable(name="users_details_games", joinColumns = @JoinColumn(name=("users_details")), inverseJoinColumns = @JoinColumn(name="games_id"))
    private List<Game> game;
}
