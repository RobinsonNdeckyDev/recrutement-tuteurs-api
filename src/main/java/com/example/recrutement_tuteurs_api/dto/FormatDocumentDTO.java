package com.example.recrutement_tuteurs_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FormatDocumentDTO {
    private Long id;
    private String nomFormat; // Ex: "PDF", "DOCX", "XLSX"
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
