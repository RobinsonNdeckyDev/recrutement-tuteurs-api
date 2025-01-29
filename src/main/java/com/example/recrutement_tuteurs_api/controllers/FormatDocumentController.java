package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.FormatDocument;
import com.example.recrutement_tuteurs_api.services.FormatDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formats_documents")
public class FormatDocumentController {
    private final FormatDocumentService formatDocumentService;

    @Autowired
    public FormatDocumentController(FormatDocumentService formatDocumentService) {
        this.formatDocumentService = formatDocumentService;
    }

    @PostMapping
    public ResponseEntity<FormatDocument> createFormatDocument(@RequestBody FormatDocument formatDocument) {
        return new ResponseEntity<>(formatDocumentService.createFormatDocument(formatDocument), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<FormatDocument>> getAllFormatDocuments() {
        return new ResponseEntity<>(formatDocumentService.getAllFormatDocuments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormatDocument> getFormatDocumentById(@PathVariable Long id) {
        return formatDocumentService.getFormatDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormatDocument> updateFormatDocument(@PathVariable Long id, @RequestBody FormatDocument updatedFormatDocument) {
        if (formatDocumentService.getFormatDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedFormatDocument.setIdFormatDocument(id);
        return new ResponseEntity<>(formatDocumentService.updateFormatDocument(id, updatedFormatDocument), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFormatDocument(@PathVariable Long id) {
        if (formatDocumentService.getFormatDocumentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        formatDocumentService.deleteFormatDocumentById(id);
        return ResponseEntity.noContent().build();
    }
}