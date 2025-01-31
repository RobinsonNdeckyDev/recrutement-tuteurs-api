package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.AnneeAnnonce;
import com.example.recrutement_tuteurs_api.services.AnneeAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent créer une année d'annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AnneeAnnonce> createAnneeAnnonce(@RequestBody AnneeAnnonce anneeAnnonce) {
        return new ResponseEntity<>(anneeAnnonceService.createAnneeAnnonce(anneeAnnonce), HttpStatus.CREATED);
    }

    // Tout le monde peut voir toutes les années d'annonces
    @GetMapping
    public ResponseEntity<Iterable<AnneeAnnonce>> getAllAnneeAnnonces() {
        return new ResponseEntity<>(anneeAnnonceService.getAllAnneeAnnonces(), HttpStatus.OK);
    }

    // Tout le monde peut voir une année d'annonce spécifique
    @GetMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> getAnneeAnnonceById(@PathVariable Long id) {
        return anneeAnnonceService.getAnneeAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent modifier une annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> updateAnneeAnnonce(@PathVariable Long id, @RequestBody AnneeAnnonce updatedAnneeAnnonce) {
        if (anneeAnnonceService.getAnneeAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedAnneeAnnonce.setId_annee(id);
        return new ResponseEntity<>(anneeAnnonceService.updateAnneeAnnonce(id, updatedAnneeAnnonce), HttpStatus.OK);
    }

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent supprimer une année d'annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnneeAnnonce(@PathVariable Long id) {
        if (anneeAnnonceService.getAnneeAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        anneeAnnonceService.deleteAnneeAnnonceById(id);
        return ResponseEntity.noContent().build();
    }
}