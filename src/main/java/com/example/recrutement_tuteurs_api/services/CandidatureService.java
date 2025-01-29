// src/main/java/com/example/recrutement_tuteurs_api/services/CandidatureService.java
package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Candidature;
import com.example.recrutement_tuteurs_api.repository.CandidatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {
    private final CandidatureRepository candidatureRepository;

    public CandidatureService(CandidatureRepository candidatureRepository) {
        this.candidatureRepository = candidatureRepository;
    }

    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    public Candidature createCandidature(Candidature candidature) {
        candidature.setEtat("EN_ATTENTE");
        return candidatureRepository.save(candidature);
    }

    public Optional<Candidature> getCandidatureById(Long id) {
        return candidatureRepository.findById(id);
    }

    public Candidature updateCandidature(Candidature updatedCandidature) {
        return candidatureRepository.save(updatedCandidature);
    }

    public void deleteCandidatureById(Long id) {
        candidatureRepository.deleteById(id);
    }
}