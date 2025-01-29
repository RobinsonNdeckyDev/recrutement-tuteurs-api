package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Annonce;
import com.example.recrutement_tuteurs_api.repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce createAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public Optional<Annonce> getAnnonceById(Long id) {
        return annonceRepository.findById(id);
    }

    public Annonce updateAnnonce(Long id, Annonce updatedAnnonce) {
        updatedAnnonce.setIdAnnonce(id);
        return annonceRepository.save(updatedAnnonce);
    }

    public void deleteAnnonceById(Long id) {
        annonceRepository.deleteById(id);
    }
}