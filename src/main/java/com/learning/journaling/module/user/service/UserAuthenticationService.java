package com.learning.journaling.module.user.service;

import com.learning.journaling.configuration.security.JwtService;
import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.user.dto.LoginRequest;
import com.learning.journaling.module.user.dto.UserAuthenticationRequest;
import com.learning.journaling.module.user.dto.UserAuthenticationResponse;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public void userRegister(UserAuthenticationRequest request){
        validation(request.getEmail());
        User user =  User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .pin(passwordEncoder.encode(request.getPin()))
                .role("user")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    public UserAuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = (User) userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return UserAuthenticationResponse.builder().type("Bearer").token(jwt).build();

    }

    private void validation(String email){
        if(userRepository.existsByEmail(email)){
            throw new BaseException(400, "Error", "Email has been used");
        }
    }

}
