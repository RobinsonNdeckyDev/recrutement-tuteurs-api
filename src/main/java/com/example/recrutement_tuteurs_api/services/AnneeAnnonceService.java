package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.AnneeAnnonce;
import com.example.recrutement_tuteurs_api.repository.AnneeAnnonceRepository;
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

    public AnneeAnnonce updateAnneeAnnonce(Long id, AnneeAnnonce updatedAnneeAnnonce) {
        updatedAnneeAnnonce.setId_annee(id);
        return anneeAnnonceRepository.save(updatedAnneeAnnonce);
    }

    public void deleteAnneeAnnonceById(Long id) {
        anneeAnnonceRepository.deleteById(id);
    }
}