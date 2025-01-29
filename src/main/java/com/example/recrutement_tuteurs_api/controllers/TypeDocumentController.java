package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.TypeDocument;
import com.example.recrutement_tuteurs_api.services.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/types_documents")
public class TypeDocumentController {
    private final TypeDocumentService typeDocumentService;

    @Autowired
    public TypeDocumentController(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    @PostMapping
    public ResponseEntity<TypeDocument> createTypeDocument(@RequestBody TypeDocument typeDocument) {
        return new ResponseEntity<>(typeDocumentService.createTypeDocument(typeDocument), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<TypeDocument>> getAllTypeDocuments() {
        return new ResponseEntity<>(typeDocumentService.getAllTypeDocuments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDocument> getTypeDocumentById(@PathVariable Long id) {
        return typeDocumentService.getTypeDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeDocument> updateTypeDocument(@PathVariable Long id, @RequestBody TypeDocument updatedTypeDocument) {
        if (typeDocumentService.getTypeDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedTypeDocument.setIdTypeDocument(id);
        return new ResponseEntity<>(typeDocumentService.updateTypeDocument(id, updatedTypeDocument), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeDocument(@PathVariable Long id) {
        if (typeDocumentService.getTypeDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        typeDocumentService.deleteTypeDocumentById(id);
        return ResponseEntity.noContent().build();
    }
}