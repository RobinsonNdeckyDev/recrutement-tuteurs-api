package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.dto.UserDTO;
import com.example.recrutement_tuteurs_api.mappers.UserMapper;
import com.example.recrutement_tuteurs_api.models.Admin;
import com.example.recrutement_tuteurs_api.models.Candidat;
import com.example.recrutement_tuteurs_api.models.Role;
import com.example.recrutement_tuteurs_api.models.User;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import com.example.recrutement_tuteurs_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CandidatRepository candidatRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AdminRepository adminRepository,
                       CandidatRepository candidatRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.candidatRepository = candidatRepository;
        this.userMapper = userMapper;
    }

    /**
     * Créer un utilisateur et l'affecte dans la bonne table selon son rôle (ADMIN ou CANDIDAT).
     */
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        // Vérifier si l'email est déjà utilisé
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // Mapper UserDTO vers User
        User user = userMapper.toEntity(userDTO);

        // Hachage du mot de passe (exemple avec BCrypt)
        user.setPassword("{bcrypt}" + user.getPassword());

        // Enregistrer l'utilisateur dans la table `users`
        User savedUser = userRepository.save(user);

        // Créer un Admin ou un Candidat selon le rôle
        if (user.getRole() == Role.ADMIN) {
            Admin admin = new Admin();
            admin.setId(savedUser.getId());  // Utiliser le même ID
            admin.setNom(savedUser.getNom());
            admin.setPrenom(savedUser.getPrenom());
            admin.setEmail(savedUser.getEmail());
            admin.setTelephone(savedUser.getTelephone());
            admin.setAdresse(savedUser.getAdresse());
            admin.setPhotoProfil(savedUser.getPhotoProfil());
            admin.setDescription(savedUser.getDescription());
            admin.setRole(savedUser.getRole());

            adminRepository.save(admin);
        } else if (user.getRole() == Role.CANDIDAT) {
            Candidat candidat = new Candidat();
            candidat.setId(savedUser.getId());  // Utiliser le même ID
            candidat.setNom(savedUser.getNom());
            candidat.setPrenom(savedUser.getPrenom());
            candidat.setEmail(savedUser.getEmail());
            candidat.setTelephone(savedUser.getTelephone());
            candidat.setAdresse(savedUser.getAdresse());
            candidat.setPhotoProfil(savedUser.getPhotoProfil());
            candidat.setDescription(savedUser.getDescription());
            candidat.setRole(savedUser.getRole());

            candidatRepository.save(candidat);
        }

        // Retourner l'utilisateur créé sous forme de DTO
        return userMapper.toDTO(savedUser);
    }

    /**
     * Récupérer un utilisateur par son ID.
     */
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    /**
     * Mettre à jour un utilisateur existant.
     */
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // Vérifier si l'utilisateur existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));

        // Mise à jour des champs (sauf email et mot de passe)
        existingUser.setNom(userDTO.getNom());
        existingUser.setPrenom(userDTO.getPrenom());
        existingUser.setTelephone(userDTO.getTelephone());
        existingUser.setAdresse(userDTO.getAdresse());
        existingUser.setPhotoProfil(userDTO.getPhotoProfil());
        existingUser.setDescription(userDTO.getDescription());

        // Vérifier si le mot de passe doit être mis à jour
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword("{bcrypt}" + userDTO.getPassword());
        }

        // Sauvegarde dans la table `users`
        User updatedUser = userRepository.save(existingUser);

        // Mise à jour de l'Admin ou du Candidat correspondant
        if (existingUser.getRole() == Role.ADMIN) {
            Admin admin = adminRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Admin non trouvé !"));
            admin.setNom(updatedUser.getNom());
            admin.setPrenom(updatedUser.getPrenom());
            admin.setTelephone(updatedUser.getTelephone());
            admin.setAdresse(updatedUser.getAdresse());
            admin.setPhotoProfil(updatedUser.getPhotoProfil());
            admin.setDescription(updatedUser.getDescription());
            adminRepository.save(admin);
        } else if (existingUser.getRole() == Role.CANDIDAT) {
            Candidat candidat = candidatRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé !"));
            candidat.setNom(updatedUser.getNom());
            candidat.setPrenom(updatedUser.getPrenom());
            candidat.setTelephone(updatedUser.getTelephone());
            candidat.setAdresse(updatedUser.getAdresse());
            candidat.setPhotoProfil(updatedUser.getPhotoProfil());
            candidat.setDescription(updatedUser.getDescription());
            candidatRepository.save(candidat);
        }

        // Retourner l'utilisateur mis à jour sous forme de DTO
        return userMapper.toDTO(updatedUser);
    }


    /**
     * Supprimer un utilisateur par son ID.
     */
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
