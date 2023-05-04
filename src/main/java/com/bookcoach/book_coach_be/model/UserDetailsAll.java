package com.bookcoach.book_coach_be.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_details_all")
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
    private List<Game> game;

}
