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

    // R�cup�rer les informations de l'utilisateur connect�
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        // R�cup�rer l'ID de l'utilisateur connect� depuis le contexte de s�curit�
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // Obtenir l'utilisateur par ID
        UserDTO user = userService.getUserById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouv�"));

        // Retourner les informations de l'utilisateur
        return ResponseEntity.ok(user);
    }
}
