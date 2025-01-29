package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "annees_annonces")
@Data
@NoArgsConstructor
public class AnneeAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_annee;

    @Column(nullable = false, unique = true)
    private String annee; // Format attendu : "2024-2025"

    private LocalDate dateCreation = LocalDate.now(); // Date par défaut lors de l'insertion

    private LocalDate dateModification;

    // Getters and Setters
    public Long getId_annee() {
        return id_annee;
    }

    public void setId_annee(Long id_annee) {
        this.id_annee = id_annee;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }
}