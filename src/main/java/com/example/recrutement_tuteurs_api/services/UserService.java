package com.example.recrutement_tuteurs_api.services;

    import com.example.recrutement_tuteurs_api.models.Admin;
    import com.example.recrutement_tuteurs_api.models.Candidat;
    import com.example.recrutement_tuteurs_api.models.Role;
    import com.example.recrutement_tuteurs_api.models.User;
    import com.example.recrutement_tuteurs_api.repository.AdminRepository;
    import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
    import com.example.recrutement_tuteurs_api.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private AdminRepository adminRepository;

        @Autowired
        private CandidatRepository candidatRepository;

        public User registerUser(String email, String password, String role, String nom, String prenom, String telephone, String adresse) {
            // Créer l'utilisateur de base
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(Role.valueOf(role));

            // Enregistrer l'utilisateur dans la table `users`
            User savedUser = userRepository.save(user);

            // Ajouter dans la table spécifique selon le rôle
            if (role.equals("ADMIN")) {
                Admin admin = new Admin();
                admin.setNom(nom);
                admin.setPrenom(prenom);
                admin.setTelephone(telephone);
                admin.setAdresse(adresse);
                admin.setUser(savedUser);
                adminRepository.save(admin);
            } else if (role.equals("CANDIDAT")) {
                Candidat candidat = new Candidat();
                candidat.setNom(nom);
                candidat.setPrenom(prenom);
                candidat.setTelephone(telephone);
                candidat.setAdresse(adresse);
                candidat.setUser(savedUser);
                candidatRepository.save(candidat);
            }

            return savedUser;
        }
    }