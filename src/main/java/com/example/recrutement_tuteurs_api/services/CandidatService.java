package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Candidat;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {
    private final CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat createCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public Optional<Candidat> getCandidatById(Long id) {
        return candidatRepository.findById(id);
    }

    public Candidat updateCandidat(Long id, Candidat updatedCandidat) {
        updatedCandidat.setId(id);
        return candidatRepository.save(updatedCandidat);
    }

    public void deleteCandidatById(Long id) {
        candidatRepository.deleteById(id);
    }
}