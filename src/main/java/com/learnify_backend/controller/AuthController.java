package com.learnify_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnify_backend.dto.JwtAuthResponse;
import com.learnify_backend.dto.LoginDto;
import com.learnify_backend.dto.OtpDto;
import com.learnify_backend.dto.RegisterDto;
import com.learnify_backend.service.AuthService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto){
        System.out.println("REGISTER HIT in controller......................................................................................................................................................................................." );
        System.out.println("role" + dto.getRole());
        System.out.println(".........................................................................................................");
        return new ResponseEntity<>(authService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto dto){
        System.out.println("LOGIN HIT in controller");
        System.out.println(passwordEncoder.encode(dto.getPassword()));
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpDto dto){
        System.out.println("SEND OTP HIT in controller");
        authService.sendOtp(dto.getEmail());
        return ResponseEntity.ok("OTP sent successfully");
    }
}
