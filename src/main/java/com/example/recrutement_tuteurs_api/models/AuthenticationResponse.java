package com.example.recrutement_tuteurs_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserInfo userInfo;

    @Data
    @AllArgsConstructor
    public static class UserInfo {
        private String prenom;
        private String nom;
        private String email;
        private String role;
        private String telephone;
        private String photoProfil;
        private String description;
    }
}















//package com.example.recrutement_tuteurs_api.models;
//
//public class AuthenticationResponse {
//    private String token;
//    private UserInfo infosUser; // Regroupe les infos utilisateur
//
//    public AuthenticationResponse(String token, UserInfo infosUser) {
//        this.token = token;
//        this.infosUser = infosUser;
//    }
//
//    public AuthenticationResponse(String token, String prenom, String nom, String email, String role, String telephone, String photoProfil, String description) {
//        this.token = token;
//        this.infosUser = new UserInfo(prenom, nom, email, role, telephone, photoProfil, description);
//    }
//
//    // Getters et Setters
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public UserInfo getInfosUser() {
//        return infosUser;
//    }
//
//    public void setInfosUser(UserInfo infosUser) {
//        this.infosUser = infosUser;
//    }
//
//    // Classe interne pour stocker les informations utilisateur
//    public static class UserInfo {
//        private String prenom;
//        private String nom;
//        private String email;
//        private String role;
//        private String telephone;
//        // private String adresse;
//        private String photoProfil;
//        private String description;
//
//        public UserInfo(String prenom, String nom, String email, String role, String telephone, String photoProfil, String description) {
//            this.prenom = prenom;
//            this.nom = nom;
//            this.email = email;
//            this.role = role;
//            this.telephone = telephone;
//            // this.adresse = adresse;
//            this.photoProfil = photoProfil;
//            this.description = description;
//        }
//
//        // Getters et Setters
//        public String getPrenom() { return prenom; }
//        public void setPrenom(String prenom) { this.prenom = prenom; }
//
//        public String getNom() { return nom; }
//        public void setNom(String nom) { this.nom = nom; }
//
//        public String getEmail() { return email; }
//        public void setEmail(String email) { this.email = email; }
//
//        public String getRole() { return role; }
//        public void setRole(String role) { this.role = role; }
//
//        public String getTelephone() { return telephone; }
//        public void setTelephone(String telephone) { this.telephone = telephone; }
//
////        public String getAdresse() { return adresse; }
////        public void setAdresse(String adresse) { this.adresse = adresse; }
//
//        public String getPhotoProfil() { return photoProfil; }
//        public void setPhotoProfil(String photoProfil) { this.photoProfil = photoProfil; }
//
//        public String getDescription() { return description; }
//        public void setDescription(String description) { this.description = description; }
//    }
//}
