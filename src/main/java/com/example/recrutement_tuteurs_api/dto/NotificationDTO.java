package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private String message;
    private Long idCandidat;   // Candidat qui reçoit la notification
    private Long idAdmin;      // Admin qui envoie la notification
    private Long idCandidature; // Candidature concernée par la notification
    private Boolean estLue = false; // Indique si le candidat a vu la notification
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
