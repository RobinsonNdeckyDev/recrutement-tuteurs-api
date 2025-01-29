package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.AnneeAnnonce;
import com.example.recrutement_tuteurs_api.services.AnneeAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/annees_annonces")
public class AnneeAnnonceController {
    private final AnneeAnnonceService anneeAnnonceService;

    @Autowired
    public AnneeAnnonceController(AnneeAnnonceService anneeAnnonceService) {
        this.anneeAnnonceService = anneeAnnonceService;
    }

    @PostMapping
    public ResponseEntity<AnneeAnnonce> createAnneeAnnonce(@RequestBody AnneeAnnonce anneeAnnonce) {
        return new ResponseEntity<>(anneeAnnonceService.createAnneeAnnonce(anneeAnnonce), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<AnneeAnnonce>> getAllAnneeAnnonces() {
        return new ResponseEntity<>(anneeAnnonceService.getAllAnneeAnnonces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> getAnneeAnnonceById(@PathVariable Long id) {
        return anneeAnnonceService.getAnneeAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> updateAnneeAnnonce(@PathVariable Long id, @RequestBody AnneeAnnonce updatedAnneeAnnonce) {
        if (anneeAnnonceService.getAnneeAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedAnneeAnnonce.setId_annee(id);
        return new ResponseEntity<>(anneeAnnonceService.updateAnneeAnnonce(id, updatedAnneeAnnonce), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnneeAnnonce(@PathVariable Long id) {
        if (anneeAnnonceService.getAnneeAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        anneeAnnonceService.deleteAnneeAnnonceById(id);
        return ResponseEntity.noContent().build();
    }
}