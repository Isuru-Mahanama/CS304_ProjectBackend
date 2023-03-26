package com.example.demo.Service;

import com.example.demo.Model.Role;
import com.example.demo.Model.Token;
import com.example.demo.Model.TokenType;
import com.example.demo.Model.User;
import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.TokenRepo;
import com.example.demo.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    @Autowired
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepo userRepo;
    private final TokenRepo tokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
   public AuthenticationResponse registerAdmin(UserDTO request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse  authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserID());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse  refreshToken(HttpServletRequest request){


        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Authorization header missing or invalid");
        }

        jwt = authHeader.substring(7);
        System.out.println(jwt);
        userEmail = jwtService.extractUsername(jwt);
        System.out.println(userEmail);
        if (userEmail != null ) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("Refresh token  generated");

            if (jwtService.isTokenValid(jwt, userDetails) ) {
                var user = userRepo.findByEmail(userEmail)
                        .orElseThrow();
                System.out.println(2);
                var jwtToken = jwtService.generateToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user, jwtToken);
                System.out.println("Refresh token  generated");
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .refreshToken(jwt)
                        .build();
            }


            }

        throw new RuntimeException("RefreshToken is missing");
    }

}
