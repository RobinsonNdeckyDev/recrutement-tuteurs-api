package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Admin;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        updatedAdmin.setIdAdmin(id);
        return adminRepository.save(updatedAdmin);
    }

    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}