// src/main/java/com/example/recrutement_tuteurs_api/repository/CandidatureRepository.java
package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    // Trouver toutes les candidatures d'un candidat'
    // ...
    // Exemples de méthodes de requêtes personnalisées

}