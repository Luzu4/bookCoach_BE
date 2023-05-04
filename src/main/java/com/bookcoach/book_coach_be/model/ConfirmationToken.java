package com.bookcoach.book_coach_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public User getUserEntity() {
        return user;
    }

    public void setUserEntity(User user) {
        this.user = user;
    }
}