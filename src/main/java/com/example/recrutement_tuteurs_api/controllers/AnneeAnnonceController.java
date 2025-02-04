package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.AnneeAnnonce;
import com.example.recrutement_tuteurs_api.services.AnneeAnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/annees_annonces")
@Tag(name = "Ann�es Annonces", description = "Endpoints de gestion des ann�es d'annonces")
public class AnneeAnnonceController {

    private final AnneeAnnonceService anneeAnnonceService;

    @Autowired
    public AnneeAnnonceController(AnneeAnnonceService anneeAnnonceService) {
        this.anneeAnnonceService = anneeAnnonceService;
    }

    @Operation(summary = "Cr�er une ann�e d'annonce", description = "Seuls les administrateurs peuvent cr�er une nouvelle ann�e d'annonce.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ann�e d'annonce cr��e avec succ�s"),
            @ApiResponse(responseCode = "403", description = "Acc�s interdit"),
            @ApiResponse(responseCode = "400", description = "Requ�te invalide")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AnneeAnnonce> createAnneeAnnonce(@RequestBody AnneeAnnonce anneeAnnonce) {
        return new ResponseEntity<>(anneeAnnonceService.createAnneeAnnonce(anneeAnnonce), HttpStatus.CREATED);
    }

    @Operation(summary = "R�cup�rer toutes les ann�es d'annonces", description = "Tout le monde peut voir la liste compl�te des ann�es d'annonces.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des ann�es d'annonces r�cup�r�e avec succ�s")
    })
    @GetMapping
    public ResponseEntity<Iterable<AnneeAnnonce>> getAllAnneeAnnonces() {
        return new ResponseEntity<>(anneeAnnonceService.getAllAnneeAnnonces(), HttpStatus.OK);
    }

    @Operation(summary = "R�cup�rer une ann�e d'annonce par ID", description = "Permet d'afficher une ann�e d'annonce sp�cifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ann�e d'annonce r�cup�r�e avec succ�s"),
            @ApiResponse(responseCode = "404", description = "Ann�e d'annonce non trouv�e")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> getAnneeAnnonceById(@PathVariable Long id) {
        Optional<AnneeAnnonce> anneeAnnonce = anneeAnnonceService.getAnneeAnnonceById(id);
        return anneeAnnonce.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre � jour une ann�e d'annonce", description = "Seuls les administrateurs peuvent modifier une ann�e d'annonce existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ann�e d'annonce mise � jour avec succ�s"),
            @ApiResponse(responseCode = "404", description = "Ann�e d'annonce non trouv�e"),
            @ApiResponse(responseCode = "403", description = "Acc�s interdit"),
            @ApiResponse(responseCode = "400", description = "Requ�te invalide")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> updateAnneeAnnonce(@PathVariable Long id, @RequestBody AnneeAnnonce updatedAnneeAnnonce) {
        if (anneeAnnonceService.getAnneeAnnonceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedAnneeAnnonce.setId_annee(id);
        return new ResponseEntity<>(anneeAnnonceService.updateAnneeAnnonce(id, updatedAnneeAnnonce), HttpStatus.OK);
    }

    @Operation(summary = "Supprimer une ann�e d'annonce", description = "Seuls les administrateurs peuvent supprimer une ann�e d'annonce.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ann�e d'annonce supprim�e avec succ�s"),
            @ApiResponse(responseCode = "404", description = "Ann�e d'annonce non trouv�e"),
            @ApiResponse(responseCode = "403", description = "Acc�s interdit")
    })
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
