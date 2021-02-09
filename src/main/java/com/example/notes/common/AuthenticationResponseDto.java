package com.example.notes.common;

public class AuthenticationResponseDto {
    public String getJwt() {
        return jwt;
    }

    private final String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }

}
