package com.example.recrutement_tuteurs_api.models;

import lombok.Getter;

public class AuthenticationRequest {
    @Getter
    private String email;
    private String password;
    private String role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}