package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "annonces")
@Data
@NoArgsConstructor
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDate dateLimite;

    @Column(nullable = false)
    private String etat = "OUVERTE"; // Par défaut, l'annonce est ouverte

    @ManyToOne
    @JoinColumn(name = "id_annee", nullable = false)
    private AnneeAnnonce anneeAcademique;

    // Getters and Setters
    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public AnneeAnnonce getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAnnonce anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }
}