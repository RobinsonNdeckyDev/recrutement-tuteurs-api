package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnnonceDTO {
    private Long id;
    private String titre;
    private String description;
    private String contenu;
    private String auteur;
    private String image;
    private String etat;
    private LocalDateTime dateLimite;
    private Long idAnnee;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
