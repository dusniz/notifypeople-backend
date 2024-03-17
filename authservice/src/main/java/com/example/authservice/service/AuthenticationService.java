package com.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.authservice.auth.AuthenticationRequest;
import com.example.authservice.auth.AuthenticationResponse;
import com.example.authservice.auth.RegisterRequest;
import com.example.authservice.model.Role;
import com.example.authservice.model.User;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        service.saveUser(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);
        Optional<User> user = service.findUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(
                user.orElseThrow(() -> new NoSuchElementException("no user with such username"))
        );
        return AuthenticationResponse.builder()
                .user(user.orElseThrow(() -> new NoSuchElementException("no user with such username")))
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = service.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }
}
