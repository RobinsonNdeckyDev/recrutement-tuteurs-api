package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Recherche un utilisateur par email
    Optional<User> findByEmail(String email);

    // Vérifie si un email existe pour un utilisateur
    boolean existsByEmail(String email);
}