package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.Annonce;
import com.example.recrutement_tuteurs_api.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {
    private final AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent créer une annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        return new ResponseEntity<>(annonceService.createAnnonce(annonce), HttpStatus.CREATED);
    }

    // Tout le monde peut voir toutes les annonces
    @GetMapping
    public ResponseEntity<Iterable<Annonce>> getAllAnnonces() {
        return new ResponseEntity<>(annonceService.getAllAnnonces(), HttpStatus.OK);
    }

    // Tout le monde peut voir une annonce spécifique
    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent modifier une annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Long id, @RequestBody Annonce updatedAnnonce) {
        if (annonceService.getAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedAnnonce.setIdAnnonce(id);
        return new ResponseEntity<>(annonceService.updateAnnonce(id, updatedAnnonce), HttpStatus.OK);
    }

    // Seuls les utilisateurs ayant le rôle 'ADMIN' peuvent supprimer une annonce
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable Long id) {
        if (annonceService.getAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        annonceService.deleteAnnonceById(id);
        return ResponseEntity.noContent().build();
    }
}