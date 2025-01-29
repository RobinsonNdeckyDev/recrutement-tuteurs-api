package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formats_documents")
@Data
@NoArgsConstructor
public class FormatDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormatDocument;

    @Column(nullable = false, unique = true)
    private String nomFormat; // Exemple : PDF, PNG

    private String description;

    // Getters and Setters
    public Long getIdFormatDocument() {
        return idFormatDocument;
    }

    public void setIdFormatDocument(Long idFormatDocument) {
        this.idFormatDocument = idFormatDocument;
    }

    public String getNomFormat() {
        return nomFormat;
    }

    public void setNomFormat(String nomFormat) {
        this.nomFormat = nomFormat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}