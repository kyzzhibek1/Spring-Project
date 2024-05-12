package com.example.project.service;

import com.example.project.controller.InvalidDataException;
import com.example.project.controller.NotFoundException;
import com.example.project.dto.AuthLoginDTO;
import com.example.project.dto.AuthRegisterDTO;
import com.example.project.dto.AuthTokenDTO;
import com.example.project.entity.RefreshToken;
import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceJPA implements  AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void register(AuthRegisterDTO authRegisterDTO) {
        if (!authRegisterDTO.getPassword().equals(authRegisterDTO.getConfirmPassword())) {
            throw new InvalidDataException("Passwords does not match");
        }

        User user = userMapper.authDtoToUser(authRegisterDTO);
        user.setPassword(passwordEncoder.encode(authRegisterDTO.getPassword()));

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Phone number is already taken");
        }
    }

    @Override
    public AuthTokenDTO login(AuthLoginDTO authLoginDTO) {
        User user = userRepository.findByPhoneNumber(authLoginDTO.getPhoneNumber())
                .orElseThrow(() -> new NotFoundException("User with that number not found"));

        if (!passwordEncoder.matches(authLoginDTO.getPassword(), user.getPassword())) {
            throw new InvalidDataException("Incorrect password");
        }

        String jwtToken = jwtService.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authLoginDTO.getPhoneNumber());
        AuthTokenDTO response = new AuthTokenDTO(jwtToken, refreshToken.getToken());
        return response;
    }

    @Override
    public AuthTokenDTO refreshToken(String refreshToken) {
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getUsername());
                    return new AuthTokenDTO(accessToken, refreshToken);
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB!"));
    }
}
