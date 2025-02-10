package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TypeDocumentDTO {
    private Long id;
    private String libelle;
    private String description;
    private Boolean estObligatoire;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
