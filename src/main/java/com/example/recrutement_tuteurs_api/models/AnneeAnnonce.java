package com.example.recrutement_tuteurs_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Entity
@Table(name = "annees_annonces")
@Data
@NoArgsConstructor
public class AnneeAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore  // Exclut id_annee des réponses JSON
//    @Schema(hidden = true)  // Cache id_annee dans Swagger
    private Long id_annee;

    @Column(nullable = false, unique = true)
    private String annee; // Format attendu : "2024-2025"

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateModification;

    // Getters et Setters
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }
}
