package com.example.recrutement_tuteurs_api.mappers;

import com.example.recrutement_tuteurs_api.dto.CandidatDTO;
import com.example.recrutement_tuteurs_api.models.Candidat;
import org.springframework.stereotype.Component;


@Component
public class CandidatMapper {

    // Méthode pour convertir un Candidat en CandidatDTO
    public CandidatDTO toDTO(Candidat candidat) {
        if (candidat == null) {
            return null;
        }

        CandidatDTO candidatDTO = new CandidatDTO();

        // Mapper les propriétés spécifiques de Candidat
        candidatDTO.setNom(candidat.getNom());
        candidatDTO.setPrenom(candidat.getPrenom());
        candidatDTO.setTelephone(candidat.getTelephone());
        candidatDTO.setAdresse(candidat.getAdresse());
        candidatDTO.setDescription(candidat.getDescription());
        candidatDTO.setPhotoProfil(candidat.getPhotoProfil());
        candidatDTO.setCvUrl(candidat.getCvUrl());
        candidatDTO.setEmail(candidat.getEmail());
        candidatDTO.setDomaineEtude(candidat.getDomaineEtude());
        candidatDTO.setNiveauEtude(candidat.getNiveauEtude());

        return candidatDTO;
    }

    // Méthode pour convertir un CandidatDTO en Candidat
    public Candidat toEntity(CandidatDTO candidatDTO) {
        if (candidatDTO == null) {
            return null;
        }

        Candidat candidat = new Candidat();

        // Mapper les propriétés spécifiques de Candidat
        candidat.setNom(candidatDTO.getNom());
        candidat.setPrenom(candidatDTO.getPrenom());
        candidat.setTelephone(candidatDTO.getTelephone());
        candidat.setAdresse(candidatDTO.getAdresse());
        candidat.setDescription(candidatDTO.getDescription());
        candidat.setPhotoProfil(candidatDTO.getPhotoProfil());
        candidat.setSiteUrl(candidatDTO.getSiteUrl());
        candidat.setCvUrl(candidatDTO.getCvUrl());
        candidat.setEmail(candidatDTO.getEmail());
        candidat.setDomaineEtude(candidatDTO.getDomaineEtude());
        candidat.setNiveauEtude(candidatDTO.getNiveauEtude());

        return candidat;
    }

}
