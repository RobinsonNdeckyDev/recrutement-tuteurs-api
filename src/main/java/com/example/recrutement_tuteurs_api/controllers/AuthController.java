package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.dto.UserDTO;
import com.example.recrutement_tuteurs_api.models.RegisterRequest;
import com.example.recrutement_tuteurs_api.models.AuthenticationRequest;
import com.example.recrutement_tuteurs_api.models.AuthenticationResponse;
import com.example.recrutement_tuteurs_api.models.User;
import com.example.recrutement_tuteurs_api.services.AuthenticationService;
import com.example.recrutement_tuteurs_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    // Enregistrement d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            // Authentification de l'utilisateur avec ses identifiants
            User authenticationResponse = authenticationService.authenticate(request.getEmail(), request.getPassword());

            // Si l'utilisateur est authentifié avec succès, renvoyer le token
            return ResponseEntity.ok(authenticationResponse);

        } catch (Exception e) {
            // Gestion des erreurs liées à l'authentification
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides.");
        }
    }
}
