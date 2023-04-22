package com.bookcoach.book_coach_be.auth;

import com.bookcoach.book_coach_be.auth.AuthenticationRequest;
import com.bookcoach.book_coach_be.auth.AuthenticationResponse;
import com.bookcoach.book_coach_be.auth.RegisterRequest;

import com.bookcoach.book_coach_be.config.JwtService;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.model.UserDetailsAll;
import com.bookcoach.book_coach_be.repository.UserDetailsAllRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final com.bookcoach.book_coach_be.repository.UserRepository UserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsAllRepository userDetailsAllRepository;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PLAYER)
                .build();
        var userDetailsAll = UserDetailsAll.builder()
                .language("")
                .city("")
                .country("")
                .build();
        if(UserRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists!");
        }

        userDetailsAllRepository.save(userDetailsAll);
        user.setUserDetails(userDetailsAll);
        UserRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = UserRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
