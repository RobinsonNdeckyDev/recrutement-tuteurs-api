package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "types_documents")
@Data
@NoArgsConstructor
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeDocument;

    @Column(nullable = false)
    private String nomType; // Exemple : CV, Lettre de Motivation

    private String description;

    @Column(nullable = false)
    private boolean estObligatoire; // Est-ce que ce type de document est obligatoire ?

    @ManyToOne
    @JoinColumn(name = "id_format_document", nullable = false)
    private FormatDocument formatDocument;

    private LocalDate dateCreation = LocalDate.now();
    private LocalDate dateModification;

    // Getters and Setters
    public Long getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(Long idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstObligatoire() {
        return estObligatoire;
    }

    public void setEstObligatoire(boolean estObligatoire) {
        this.estObligatoire = estObligatoire;
    }

    public FormatDocument getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(FormatDocument formatDocument) {
        this.formatDocument = formatDocument;
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