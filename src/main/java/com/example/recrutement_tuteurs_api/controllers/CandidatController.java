package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.Candidat;
import com.example.recrutement_tuteurs_api.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidat")
public class CandidatController {
    private final CandidatService candidatService;

    @Autowired
    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }


    @PostMapping
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return new ResponseEntity<>(candidatService.createCandidat(candidat), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Candidat>> getAllCandidats() {
        return new ResponseEntity<>(candidatService.getAllCandidats(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable Long id) {
        return candidatService.getCandidatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable Long id, @RequestBody Candidat updatedCandidat) {
        if (candidatService.getCandidatById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedCandidat.setId(id);
        return new ResponseEntity<>(candidatService.updateCandidat(id, updatedCandidat), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidat(@PathVariable Long id) {
        if (candidatService.getCandidatById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidatService.deleteCandidatById(id);
        return ResponseEntity.noContent().build();
    }
}