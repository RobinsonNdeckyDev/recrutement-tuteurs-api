package com.example.recrutement_tuteurs_api.models;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String prenom;
    private String nom;
    private String adresse;
    private String telephone;
    private String description;
    private String photoProfil;
    private Role role;

    // Si Lombok n'est pas fonctionnel, ajoute manuellement les getters et setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public Role getRole() {
        return role; // Assure-toi que ce getter existe
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
