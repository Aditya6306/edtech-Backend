package com.learnify_backend.dto;
import lombok.*;
// import lombok.Getter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
