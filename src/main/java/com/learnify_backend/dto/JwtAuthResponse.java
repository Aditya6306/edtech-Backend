package com.learnify_backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private UserDto user;
}
