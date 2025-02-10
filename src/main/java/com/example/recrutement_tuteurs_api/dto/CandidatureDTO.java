package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CandidatureDTO {
    private Long id;
    private String motifRefus;
    private String etat;
    private Long idCandidat;
    private Long idAnnonce;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
