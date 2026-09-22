package com.learnify_backend.dto;

import com.learnify_backend.entity.OTP;
import com.learnify_backend.entity.Role;

import lombok.*;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    // private String phoneNo;
    private String role;
    private String otp;
    // private String image;
}
