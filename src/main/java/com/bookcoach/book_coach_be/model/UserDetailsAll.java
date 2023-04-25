package com.bookcoach.book_coach_be.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_details_all")
public class UserDetailsAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String language;
    private String country;
    private String city;
    private String description;
    private String imageUrl;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    (fetch = FetchType.EAGER)
//    @JoinTable(name="users_details_games", joinColumns = @JoinColumn(name=("users_details")), inverseJoinColumns = @JoinColumn(name="games_id"))
    private List<Game> game;

}
