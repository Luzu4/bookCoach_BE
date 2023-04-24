package com.bookcoach.book_coach_be.dto;

import com.bookcoach.book_coach_be.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserRoleGamesDTO {
    private Role role;
    private Long[] gamesId;
    private long userId;
}
