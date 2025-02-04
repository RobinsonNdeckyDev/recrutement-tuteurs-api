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
@Tag(name = "Années Annonces", description = "Endpoints de gestion des années d'annonces")
public class AnneeAnnonceController {

    private final AnneeAnnonceService anneeAnnonceService;

    @Autowired
    public AnneeAnnonceController(AnneeAnnonceService anneeAnnonceService) {
        this.anneeAnnonceService = anneeAnnonceService;
    }

    @Operation(summary = "Créer une année d'annonce", description = "Seuls les administrateurs peuvent créer une nouvelle année d'annonce.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Année d'annonce créée avec succès"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AnneeAnnonce> createAnneeAnnonce(@RequestBody AnneeAnnonce anneeAnnonce) {
        return new ResponseEntity<>(anneeAnnonceService.createAnneeAnnonce(anneeAnnonce), HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer toutes les années d'annonces", description = "Tout le monde peut voir la liste complète des années d'annonces.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des années d'annonces récupérée avec succès")
    })
    @GetMapping
    public ResponseEntity<Iterable<AnneeAnnonce>> getAllAnneeAnnonces() {
        return new ResponseEntity<>(anneeAnnonceService.getAllAnneeAnnonces(), HttpStatus.OK);
    }

    @Operation(summary = "Récupérer une année d'annonce par ID", description = "Permet d'afficher une année d'annonce spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Année d'annonce récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Année d'annonce non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnneeAnnonce> getAnneeAnnonceById(@PathVariable Long id) {
        Optional<AnneeAnnonce> anneeAnnonce = anneeAnnonceService.getAnneeAnnonceById(id);
        return anneeAnnonce.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre à jour une année d'annonce", description = "Seuls les administrateurs peuvent modifier une année d'annonce existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Année d'annonce mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Année d'annonce non trouvée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
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

    @Operation(summary = "Supprimer une année d'annonce", description = "Seuls les administrateurs peuvent supprimer une année d'annonce.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Année d'annonce supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Année d'annonce non trouvée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
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
