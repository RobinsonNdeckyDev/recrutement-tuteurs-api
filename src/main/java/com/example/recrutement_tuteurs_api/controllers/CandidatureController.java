// src/main/java/com/example/recrutement_tuteurs_api/controllers/CandidatureController.java
package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.Candidature;
import com.example.recrutement_tuteurs_api.services.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {
    // Déclaration du service
    private final CandidatureService candidatureService;

    // Injection du service
    @Autowired
    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    // Créer une candidature
    @PostMapping
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature) {
        return new ResponseEntity<>(candidatureService.createCandidature(candidature), HttpStatus.CREATED);
    }

    // Lister toutes les candidatures''
    @GetMapping
    public ResponseEntity<Iterable<Candidature>> getAllCandidatures() {
        return new ResponseEntity<>(candidatureService.getAllCandidatures(), HttpStatus.OK);
    }

    // Récupérer une candidature par son identifiant''
    @GetMapping("/{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Long id) {
        return candidatureService.getCandidatureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour une candidature par son identifiant''
    @PutMapping("/{id}")
    public ResponseEntity<Candidature> updateCandidature(@PathVariable Long id, @RequestBody Candidature updatedCandidature) {
        if (candidatureService.getCandidatureById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedCandidature.setIdCandidature(id);
        return new ResponseEntity<>(candidatureService.updateCandidature(updatedCandidature), HttpStatus.OK);
    }

    // Supprimer une candidature par son identifiant''
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidature(@PathVariable Long id) {
        if (candidatureService.getCandidatureById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidatureService.deleteCandidatureById(id);
        return ResponseEntity.noContent().build();
    }
}
