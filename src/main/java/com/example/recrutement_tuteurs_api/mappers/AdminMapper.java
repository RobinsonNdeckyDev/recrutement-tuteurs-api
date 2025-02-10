package com.example.recrutement_tuteurs_api.mappers;

import com.example.recrutement_tuteurs_api.dto.AdminDTO;
import com.example.recrutement_tuteurs_api.models.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDto(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setNom(admin.getNom());
        adminDTO.setPrenom(admin.getPrenom());
        adminDTO.setTelephone(admin.getTelephone());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setAdresse(admin.getAdresse());
        adminDTO.setDescription(admin.getDescription());
        adminDTO.setPhotoProfil(admin.getPhotoProfil());
        adminDTO.setRole(admin.getRole());

        return adminDTO;
    }

    public Admin toEntity(AdminDTO adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setNom(adminDTO.getNom());
        admin.setPrenom(adminDTO.getPrenom());
        admin.setTelephone(adminDTO.getTelephone());
        admin.setAdresse(adminDTO.getAdresse());
        admin.setDescription(adminDTO.getDescription());
        admin.setPhotoProfil(adminDTO.getPhotoProfil());
        admin.setRole(adminDTO.getRole());
        admin.setEmail(adminDTO.getEmail());

        return admin;
    }
}

