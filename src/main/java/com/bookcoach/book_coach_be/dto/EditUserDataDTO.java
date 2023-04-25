package com.bookcoach.book_coach_be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserDataDTO {

    private String city;
    private String country;
    private String description;
    private String email;
    private String imageUrl;
    private String language;
    private String nickName;
    private String password;

    @Override
    public String toString() {
        return "EditUserDataDTO{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", language='" + language + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
