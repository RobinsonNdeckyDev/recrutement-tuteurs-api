package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.TypeDocument;
import com.example.recrutement_tuteurs_api.repository.TypeDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDocumentService {
    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentService(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    public List<TypeDocument> getAllTypeDocuments() {
        return typeDocumentRepository.findAll();
    }

    public TypeDocument createTypeDocument(TypeDocument typeDocument) {
        return typeDocumentRepository.save(typeDocument);
    }

    public Optional<TypeDocument> getTypeDocumentById(Long id) {
        return typeDocumentRepository.findById(id);
    }

    public TypeDocument updateTypeDocument(Long id, TypeDocument updatedTypeDocument) {
        updatedTypeDocument.setIdTypeDocument(id);
        return typeDocumentRepository.save(updatedTypeDocument);
    }

    public void deleteTypeDocumentById(Long id) {
        typeDocumentRepository.deleteById(id);
    }
}