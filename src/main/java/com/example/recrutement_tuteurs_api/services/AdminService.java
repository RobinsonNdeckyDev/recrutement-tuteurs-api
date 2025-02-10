package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.dto.AdminDTO;
import com.example.recrutement_tuteurs_api.mappers.AdminMapper;
import com.example.recrutement_tuteurs_api.models.Admin;
import com.example.recrutement_tuteurs_api.models.Role;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Transactional
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        // Vérifier si l'email existe déjà
        if (adminRepository.findByEmail(adminDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // Mapper AdminDTO vers Admin
        Admin admin = adminMapper.toEntity(adminDTO);

        // Hachage du mot de passe (exemple avec BCrypt)
        admin.setPassword("{bcrypt}" + admin.getPassword());

        // Définir le rôle
        admin.setRole(Role.ADMIN);

        // Enregistrer l'administrateur
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toDto(savedAdmin);
    }


    /**
     * Mettre à jour un administrateur.
     */
    @Transactional
    public AdminDTO updateAdmin(AdminDTO adminDTO, Long id) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur non trouvé !"));

        // Mise à jour des champs (email et rôle ne changent pas)
        existingAdmin.setNom(adminDTO.getNom());
        existingAdmin.setPrenom(adminDTO.getPrenom());
        existingAdmin.setTelephone(adminDTO.getTelephone());
        existingAdmin.setAdresse(adminDTO.getAdresse());
        existingAdmin.setPhotoProfil(adminDTO.getPhotoProfil());
        existingAdmin.setDescription(adminDTO.getDescription());

        Admin updatedAdmin = adminRepository.save(existingAdmin);

        return adminMapper.toDto(updatedAdmin);
    }

    /**
     * Supprimer un administrateur.
     */
    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    /**
     * Récupérer un administrateur par son ID.
     */
    public Optional<AdminDTO> getAdminById(Long id) {
        return adminRepository.findById(id).map(adminMapper::toDto);
    }
}
