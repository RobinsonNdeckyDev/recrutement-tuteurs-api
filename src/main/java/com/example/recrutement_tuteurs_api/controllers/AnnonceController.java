package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.Annonce;
import com.example.recrutement_tuteurs_api.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        return new ResponseEntity<>(annonceService.createAnnonce(annonce), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Annonce>> getAllAnnonces() {
        return new ResponseEntity<>(annonceService.getAllAnnonces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Long id, @RequestBody Annonce updatedAnnonce) {
        if (annonceService.getAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedAnnonce.setIdAnnonce(id);
        return new ResponseEntity<>(annonceService.updateAnnonce(id, updatedAnnonce), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable Long id) {
        if (annonceService.getAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        annonceService.deleteAnnonceById(id);
        return ResponseEntity.noContent().build();
    }
}