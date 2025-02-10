package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.dto.CandidatDTO;
import com.example.recrutement_tuteurs_api.mappers.CandidatMapper;
import com.example.recrutement_tuteurs_api.models.Candidat;
import com.example.recrutement_tuteurs_api.models.Role;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;
    private final CandidatMapper candidatMapper;

    public CandidatService(CandidatRepository candidatRepository, CandidatMapper candidatMapper) {
        this.candidatRepository = candidatRepository;
        this.candidatMapper = candidatMapper;
    }

    @Transactional
    public CandidatDTO createCandidat(CandidatDTO candidatDTO) {
        // V�rifier si l'email existe d�j�
        if (candidatRepository.findByEmail(candidatDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est d�j� utilis� !");
        }

        // Mapper CandidatDTO vers Candidat
        Candidat candidat = candidatMapper.toEntity(candidatDTO);

        // Hachage du mot de passe
        candidat.setPassword("{bcrypt}" + candidat.getPassword());

        // D�finir le r�le
        candidat.setRole(Role.CANDIDAT);

        // Enregistrer le candidat
        Candidat savedCandidat = candidatRepository.save(candidat);

        return candidatMapper.toDTO(savedCandidat);
    }


    /**
     * Mettre � jour un candidat.
     */
    @Transactional
    public CandidatDTO updateCandidat(Long id, CandidatDTO candidatDTO) {
        Candidat existingCandidat = candidatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouv� !"));

        // Mise � jour des champs (email et r�le ne changent pas)
        existingCandidat.setNom(candidatDTO.getNom());
        existingCandidat.setPrenom(candidatDTO.getPrenom());
        existingCandidat.setTelephone(candidatDTO.getTelephone());
        existingCandidat.setAdresse(candidatDTO.getAdresse());
        existingCandidat.setPhotoProfil(candidatDTO.getPhotoProfil());
        existingCandidat.setDescription(candidatDTO.getDescription());
        existingCandidat.setRole(candidatDTO.getRole());
        existingCandidat.setCvUrl(candidatDTO.getCvUrl());
        existingCandidat.setNiveauEtude(candidatDTO.getNiveauEtude());
        existingCandidat.setDomaineEtude(candidatDTO.getDomaineEtude());

        Candidat updatedCandidat = candidatRepository.save(existingCandidat);

        return candidatMapper.toDTO(updatedCandidat);
    }

    /**
     * Supprimer un candidat.
     */
    @Transactional
    public void deleteCandidat(Long id) {
        candidatRepository.deleteById(id);
    }

    /**
     * R�cup�rer un candidat par son ID.
     */
    public Optional<CandidatDTO> getCandidatById(Long id) {
        return candidatRepository.findById(id).map(candidatMapper::toDTO);
    }
}
