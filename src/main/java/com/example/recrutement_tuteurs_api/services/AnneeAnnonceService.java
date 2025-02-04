package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.AnneeAnnonce;
import com.example.recrutement_tuteurs_api.repository.AnneeAnnonceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnneeAnnonceService {
    private final AnneeAnnonceRepository anneeAnnonceRepository;

    public AnneeAnnonceService(AnneeAnnonceRepository anneeAnnonceRepository) {
        this.anneeAnnonceRepository = anneeAnnonceRepository;
    }

    public List<AnneeAnnonce> getAllAnneeAnnonces() {
        return anneeAnnonceRepository.findAll();
    }

    public AnneeAnnonce createAnneeAnnonce(AnneeAnnonce anneeAnnonce) {
        return anneeAnnonceRepository.save(anneeAnnonce);
    }

    public Optional<AnneeAnnonce> getAnneeAnnonceById(Long id) {
        return anneeAnnonceRepository.findById(id);
    }

    @Transactional
    public AnneeAnnonce updateAnneeAnnonce(Long id, AnneeAnnonce updatedAnneeAnnonce) {
        return anneeAnnonceRepository.findById(id).map(existingAnneeAnnonce -> {
            existingAnneeAnnonce.setAnnee(updatedAnneeAnnonce.getAnnee());
            existingAnneeAnnonce.setDateDebut(updatedAnneeAnnonce.getDateDebut());
            existingAnneeAnnonce.setDateFin(updatedAnneeAnnonce.getDateFin());
            existingAnneeAnnonce.setDateModification(updatedAnneeAnnonce.getDateModification());
            return anneeAnnonceRepository.save(existingAnneeAnnonce);
        }).orElseThrow(() -> new RuntimeException("L'année d'annonce avec l'ID " + id + " n'existe pas."));
    }

    public void deleteAnneeAnnonceById(Long id) {
        anneeAnnonceRepository.deleteById(id);
    }
}