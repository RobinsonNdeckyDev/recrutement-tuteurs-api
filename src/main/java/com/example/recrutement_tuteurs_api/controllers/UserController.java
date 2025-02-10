package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.dto.UserDTO;
import com.example.recrutement_tuteurs_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Récupérer les informations de l'utilisateur connecté
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        // Récupérer l'ID de l'utilisateur connecté depuis le contexte de sécurité
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // Obtenir l'utilisateur par ID
        UserDTO user = userService.getUserById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Retourner les informations de l'utilisateur
        return ResponseEntity.ok(user);
    }
}
