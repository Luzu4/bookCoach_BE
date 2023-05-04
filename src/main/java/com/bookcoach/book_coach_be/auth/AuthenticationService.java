package com.bookcoach.book_coach_be.auth;

import com.bookcoach.book_coach_be.auth.AuthenticationRequest;
import com.bookcoach.book_coach_be.auth.AuthenticationResponse;
import com.bookcoach.book_coach_be.auth.RegisterRequest;

import com.bookcoach.book_coach_be.config.JwtService;
import com.bookcoach.book_coach_be.model.ConfirmationToken;
import com.bookcoach.book_coach_be.model.Role;
import com.bookcoach.book_coach_be.model.User;
import com.bookcoach.book_coach_be.model.UserDetailsAll;
import com.bookcoach.book_coach_be.repository.ConfirmationTokenRepository;
import com.bookcoach.book_coach_be.repository.UserDetailsAllRepository;
import com.bookcoach.book_coach_be.repository.UserRepository;
import com.bookcoach.book_coach_be.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    public ResponseEntity<?> register(RegisterRequest request){
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PLAYER)
                .nickName(request.getNickName())
                .build();
        var userDetailsAll = UserDetailsAll.builder()
                .language("")
                .city("")
                .country("")
                .description("")
                .imageUrl("")
                .build();
        if(UserRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists!");
        }

        userDetailsAllRepository.save(userDetailsAll);
        user.setUserDetails(userDetailsAll);
        UserRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/api/v1/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Confirm you email. Link sent on your email address");
    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = UserRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());
            user.setVerified(true);
            UserRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
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
        if(!user.isVerified()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email not verified!");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
