package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}