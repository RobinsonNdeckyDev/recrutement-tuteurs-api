package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Notification;
import com.example.recrutement_tuteurs_api.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification updateNotification(Long id, Notification updatedNotification) {
        updatedNotification.setIdNotification(id);
        return notificationRepository.save(updatedNotification);
    }

    public void deleteNotificationById(Long id) {
        notificationRepository.deleteById(id);
    }
}