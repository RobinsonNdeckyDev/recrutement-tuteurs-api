package com.example.recrutement_tuteurs_api.models;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
