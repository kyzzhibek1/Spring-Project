package com.example.project.service;

import com.example.project.dto.AuthLoginDTO;
import com.example.project.dto.AuthRegisterDTO;
import com.example.project.dto.AuthTokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    void register(@Valid @RequestBody AuthRegisterDTO authRegisterDTO);


    AuthTokenDTO login(@RequestBody AuthLoginDTO authLoginDTO);


    AuthTokenDTO refreshToken(@RequestBody String refreshToken);
}
