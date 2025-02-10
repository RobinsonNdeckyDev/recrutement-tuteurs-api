package com.example.recrutement_tuteurs_api.mappers;

import com.example.recrutement_tuteurs_api.dto.UserDTO;
import com.example.recrutement_tuteurs_api.models.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Convertir un User en UserDTO
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getPrenom(),
                user.getNom(),
                user.getTelephone(),
                user.getAdresse(),
                user.getPhotoProfil()
        );
    }

    // Convertir un UserDTO en User (sans le mot de passe)
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPrenom(userDTO.getPrenom());
        user.setNom(userDTO.getNom());
        user.setTelephone(userDTO.getTelephone());
        user.setAdresse(userDTO.getAdresse());
        user.setPhotoProfil(userDTO.getPhotoProfil());
        return user;
    }

    // Convertir une liste de Users en une liste de UserDTOs
    public List<UserDTO> toDTOs(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Convertir une liste de UserDTOs en une liste de Users
    public List<User> toEntities(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
