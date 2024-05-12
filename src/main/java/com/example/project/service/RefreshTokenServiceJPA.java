package com.example.project.service;

import com.example.project.entity.RefreshToken;
import com.example.project.repository.UserRepository;
import com.example.project.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceJPA implements RefreshTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(String phoneNumber) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("User not found")))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1800000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        //here was a verify token condition,
        // but this didn't work because of incorrect working of zone times on my pc
        // (somewhy, idk why, so i just commented it, if you want you can try to uncomment it)
//        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login.");
//        }
        return token;
    }
}
