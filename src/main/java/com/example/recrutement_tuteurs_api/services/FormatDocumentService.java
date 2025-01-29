package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.FormatDocument;
import com.example.recrutement_tuteurs_api.repository.FormatDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormatDocumentService {
    private final FormatDocumentRepository formatDocumentRepository;

    public FormatDocumentService(FormatDocumentRepository formatDocumentRepository) {
        this.formatDocumentRepository = formatDocumentRepository;
    }

    public List<FormatDocument> getAllFormatDocuments() {
        return formatDocumentRepository.findAll();
    }

    public FormatDocument createFormatDocument(FormatDocument formatDocument) {
        return formatDocumentRepository.save(formatDocument);
    }

    public Optional<FormatDocument> getFormatDocumentById(Long id) {
        return formatDocumentRepository.findById(id);
    }

    public FormatDocument updateFormatDocument(Long id, FormatDocument updatedFormatDocument) {
        updatedFormatDocument.setIdFormatDocument(id);
        return formatDocumentRepository.save(updatedFormatDocument);
    }

    public void deleteFormatDocumentById(Long id) {
        formatDocumentRepository.deleteById(id);
    }
}