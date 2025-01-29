package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.Document;
import com.example.recrutement_tuteurs_api.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        return new ResponseEntity<>(documentService.createDocument(document), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Document>> getAllDocuments() {
        return new ResponseEntity<>(documentService.getAllDocuments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document updatedDocument) {
        if (documentService.getDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedDocument.setIdDocument(id);
        return new ResponseEntity<>(documentService.updateDocument(id, updatedDocument), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        if (documentService.getDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        documentService.deleteDocumentById(id);
        return ResponseEntity.noContent().build();
    }
}