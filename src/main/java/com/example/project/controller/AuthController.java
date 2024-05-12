package com.example.project.controller;

import com.example.project.dto.AuthLoginDTO;
import com.example.project.dto.AuthRegisterDTO;
import com.example.project.dto.AuthTokenDTO;
import com.example.project.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Auth Controller",
        description = "Controller for operations with auth"
)
public class AuthController {

    @Autowired
    private AuthService service;


    @Operation(
            summary = "Registration"
    )
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody AuthRegisterDTO authRegisterDTO) {
        service.register(authRegisterDTO);
        return ResponseEntity.created(null).build();
    }

    @Operation(
            summary = "Logging in user session"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody AuthLoginDTO authLoginDTO) {
        AuthTokenDTO authTokenDTO = service.login(authLoginDTO);
        return ResponseEntity.ok(authTokenDTO);
    }

    @Operation(
            summary = "Refreshing refresh token"
    )
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthTokenDTO> refreshToken(@RequestBody String refreshToken){
        AuthTokenDTO authTokenDTO = service.refreshToken(refreshToken);
        return ResponseEntity.ok(authTokenDTO);
    }
}
