package com.bookcoach.book_coach_be.dto;

import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.UserDetailsAll;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @JsonProperty("email")
    private String email;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("userDetailsAll")
    private UserDetailsAll userDetailsAll;

}