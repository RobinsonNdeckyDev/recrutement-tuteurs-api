package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);

    public Optional<Admin> findById(Long id);

}