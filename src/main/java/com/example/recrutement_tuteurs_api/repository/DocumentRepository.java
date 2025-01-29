package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}