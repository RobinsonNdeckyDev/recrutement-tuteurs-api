package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocumentDTO {
    private Long id;
    private String nomDocument;
    private Long idTypeDocument;
    private Long idFormatDocument;
    private Long idAnnonce;
    private Long idUser;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
