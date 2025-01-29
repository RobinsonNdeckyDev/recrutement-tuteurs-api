package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Document;
import com.example.recrutement_tuteurs_api.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Document updateDocument(Long id, Document updatedDocument) {
        updatedDocument.setIdDocument(id);
        return documentRepository.save(updatedDocument);
    }

    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }
}