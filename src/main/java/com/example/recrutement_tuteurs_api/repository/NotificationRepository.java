package com.example.recrutement_tuteurs_api.repository;

import com.example.recrutement_tuteurs_api.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}