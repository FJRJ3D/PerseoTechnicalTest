package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.dto.request.LoginRequest;
import com.example.PerseoTechnicalTest.dto.request.RegisterRequest;
import com.example.PerseoTechnicalTest.dto.response.AuthResponse;
import com.example.PerseoTechnicalTest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "signup")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
