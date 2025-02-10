package com.example.recrutement_tuteurs_api.dto;

import com.example.recrutement_tuteurs_api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private String adresse;
    private String telephone;
    private String photoProfile;
    private String description;
    private Role role;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;

    public UserDTO(Long id, String email, Role role, String prenom, String nom, String telephone, String adresse, String photoProfil) {
    }

//    public UserDTO(Long id, String email, Role role, String prenom, String nom, String telephone, String adresse, String photoProfil) {
//    }

    public String getPhotoProfil() {
        return photoProfile;
    }


}
