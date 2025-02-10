package com.example.recrutement_tuteurs_api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "L'email est obligatoire.")
    @NotBlank(message = "L'email ne peut pas �tre vide.")
    private String email;

    @NotNull(message = "Le mot de passe est obligatoire.")
    @NotBlank(message = "Le mot de passe ne peut pas �tre vide.")
    private String password;

    @NotNull(message = "Le pr�nom est obligatoire.")
    @NotBlank(message = "Le pr�nom ne peut pas �tre vide.")
    private String prenom;

    @NotNull(message = "Le nom est obligatoire.")
    @NotBlank(message = "Le nom ne peut pas �tre vide.")
    private String nom;

    @NotNull(message = "L'adresse est obligatoire.")
    @NotBlank(message = "L'adresse ne peut pas �tre vide.")
    private String adresse;

    private String telephone;
    private String description;
    private String photoProfil;

    @NotNull(message = "Le r�le est obligatoire.")
    private Role role;
}
