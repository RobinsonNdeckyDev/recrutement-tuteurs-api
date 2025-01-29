// src/main/java/com/example/recrutement_tuteurs_api/models/Candidature.java
package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "candidatures")
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidature;

    @ManyToOne
    @JoinColumn(name = "id_candidat", nullable = false)
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    @Column(nullable = false)
    private String etat = "EN_ATTENTE"; // Par défaut : EN_ATTENTE

    private String motifRefus;

    // Getters and Setters

    // récupérer l'id de la candidature
    public Long getIdCandidature() {
        return idCandidature;
    }

    // modifier l'id de la candidature'
    public void setIdCandidature(Long idCandidature) {
        this.idCandidature = idCandidature;
    }

    // récupérer un candidat
    public Candidat getCandidat() {
        return candidat;
    }

    // modifier un candidat
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    // récupérer une annonce
    public Annonce getAnnonce() {
        return annonce;
    }

    // modifier une annonce
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    // récupérer l'état de la candidature'
    public String getEtat() {
        return etat;
    }

    // modifier l'état de la candidature'
    public void setEtat(String etat) {
        this.etat = etat;
    }

    // récupérer le motif de refus de la candidature''
    public String getMotifRefus() {
        return motifRefus;
    }

    // modifier le motif de refus de la candidature''
    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }
}