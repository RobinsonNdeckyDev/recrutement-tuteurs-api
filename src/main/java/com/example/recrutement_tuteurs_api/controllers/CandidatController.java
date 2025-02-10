package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.dto.CandidatDTO;
import com.example.recrutement_tuteurs_api.services.CandidatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    @Autowired
    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    // Créer un candidat
    @PostMapping
    public ResponseEntity<CandidatDTO> createCandidat(@Valid @RequestBody CandidatDTO candidatDTO) {
        CandidatDTO createdCandidat = candidatService.createCandidat(candidatDTO);
        return ResponseEntity.ok(createdCandidat);
    }

    // Récupérer un candidat par son ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidatDTO> getCandidatById(@PathVariable Long id) {
        return candidatService.getCandidatById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un candidat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Long id) {
        candidatService.deleteCandidat(id);
        return ResponseEntity.noContent().build();
    }
}
