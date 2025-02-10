package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.*;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import com.example.recrutement_tuteurs_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CandidatRepository candidatRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepository,
            AdminRepository adminRepository,
            CandidatRepository candidatRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.candidatRepository = candidatRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        // Vérifier si l'email est déjà utilisé
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("L'email est déjà utilisé.");
        }

        // Hacher le mot de passe avant l'enregistrement
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Enregistrer l'utilisateur dans la table `users`
        User savedUser = userRepository.save(user);

        // Vérifier le rôle et ajouter l'utilisateur dans la bonne table
        if (user.getRole() == Role.ADMIN) {
            Admin admin = new Admin();
            admin.setUser(savedUser);
            adminRepository.save(admin);
        } else if (user.getRole() == Role.CANDIDAT) {
            Candidat candidat = new Candidat();
            candidat.setUser(savedUser);
            candidatRepository.save(candidat);
        }

        return savedUser;
    }

    public User authenticate(String email, String password) {
        // Vérifier si l'utilisateur existe
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("Email ou mot de passe incorrect.");
        }

        User user = userOptional.get();

        // Vérifier si le mot de passe est correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Email ou mot de passe incorrect.");
        }

        return user;
    }
}























//// AuthenticationService.java
//package com.example.recrutement_tuteurs_api.services;
//
//import com.example.recrutement_tuteurs_api.models.*;
//import com.example.recrutement_tuteurs_api.repository.AdminRepository;
//import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
//import com.example.recrutement_tuteurs_api.repository.UserRepository;
//import com.example.recrutement_tuteurs_api.util.JwtUtil;
//import jakarta.transaction.Transactional;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    private final AuthenticationManager authenticationManager;
//    private final CustomUserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;
//    private final UserRepository userRepository;
//    private final AdminRepository adminRepository;
//    private final CandidatRepository candidatRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthenticationService(
//            AuthenticationManager authenticationManager,
//            CustomUserDetailsService userDetailsService,
//            JwtUtil jwtUtil,
//            UserRepository userRepository,
//            AdminRepository adminRepository,
//            CandidatRepository candidatRepository,
//            PasswordEncoder passwordEncoder
//    ) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtUtil = jwtUtil;
//        this.userRepository = userRepository;
//        this.adminRepository = adminRepository;
//        this.candidatRepository = candidatRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public AuthenticationResponse login(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        // Récupérer l'utilisateur connecté
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//
//        // Vérifier si l'utilisateur est un Admin
//        Admin admin = adminRepository.findByEmail(user.getEmail()).orElse(null);
//        if (admin != null) {
//            AuthenticationResponse.UserInfo userInfo = new AuthenticationResponse.UserInfo(
//                    admin.getPrenom(),
//                    admin.getNom(),
//                    admin.getEmail(),
//                    user.getRole().name(),
//                    admin.getTelephone(),
//                    // admin.getAdresse(),
//                    admin.getPhotoProfil(),
//                    user.getDescription()
//                    );
//            return new AuthenticationResponse(jwt, userInfo);
//        }
//
//        // Vérifier si l'utilisateur est un Candidat
//        Candidat candidat = candidatRepository.findByEmail(user.getEmail()).orElse(null);
//        if (candidat != null) {
//            AuthenticationResponse.UserInfo userInfo = new AuthenticationResponse.UserInfo(
//                    candidat.getPrenom(),
//                    candidat.getNom(),
//                    candidat.getEmail(),
//                    user.getRole().name(),
//                    candidat.getTelephone(),
//                    // candidat.getAdresse(),
//                    candidat.getPhotoProfil(),
//                    user.getDescription()
//                    );
//            return new AuthenticationResponse(jwt, userInfo);
//        }
//
//        // Si aucun Admin ou Candidat n'est trouvé, renvoyer des valeurs par défaut
//        AuthenticationResponse.UserInfo userInfo = new AuthenticationResponse.UserInfo(
//                user.getPrenom(),
//                user.getNom(),
//                user.getEmail(),
//                user.getRole().name(),
//                user.getTelephone(),
//                user.getPhotoProfil(),
//                user.getDescription()
//                );
//        return new AuthenticationResponse(jwt, userInfo);
//    }
//
//    @Transactional
//    public AuthenticationResponse register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("L'utilisateur existe déjà");
//        }
//
//        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
//            throw new IllegalArgumentException("La description est obligatoire");
//        }
//
//        if (request.getPassword().length() < 8) {
//            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères");
//        }
//
//        try {
//            String encodedPassword = passwordEncoder.encode(request.getPassword());
//            User user = new User();
//            user.setEmail(request.getEmail());
//            user.setPassword(encodedPassword);
//            user.setRole(request.getRole());
//            user.setPrenom(request.getPrenom());
//            user.setNom(request.getNom());
//            user.setTelephone(request.getTelephone());
//            // user.setAdresse(request.getAdresse());
//            user.setPhotoProfil(request.getPhotoProfil());
//            user.setDescription(request.getDescription());
//            userRepository.save(user);
//
//            if (Role.ADMIN.equals(request.getRole())) {
//                Admin admin = new Admin();
//                admin.setUser(user);
//                adminRepository.save(admin);
//            } else if (Role.CANDIDAT.equals(request.getRole())) {
//                Candidat candidat = getCandidat(request, user, encodedPassword);
//                candidatRepository.save(candidat);
//            }
//
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//            final String jwt = jwtUtil.generateToken(userDetails);
//
//            AuthenticationResponse.UserInfo userInfo = createUserInfo(user);
//            return new AuthenticationResponse(jwt, userInfo);
//        } catch (Exception e) {
//            throw new RuntimeException("Erreur lors de l'enregistrement de l'utilisateur: " + e.getMessage());
//        }
//    }
//
//    private AuthenticationResponse.UserInfo createUserInfo(User user) {
//        AuthenticationResponse.UserInfo userInfo = new AuthenticationResponse.UserInfo(
//                user.getPrenom(),
//                user.getNom(),
//                user.getEmail(),
//                user.getRole().name(),
//                user.getTelephone(),
//                user.getPhotoProfil(),
//                user.getDescription()
//        );
//        return userInfo;
//    }
//
//    private Candidat getCandidat(RegisterRequest request, User user, String encodedPassword) {
//        Candidat candidat = new Candidat();
//        candidat.setUser(user); // Assuming a relationship between Candidat and User
//        candidat.setPrenom(request.getPrenom());
//        candidat.setNom(request.getNom());
//        candidat.setTelephone(request.getTelephone());
//        //candidat.setAdresse(request.getAdresse());
//        candidat.setPhotoProfil(request.getPhotoProfil());
//        candidat.setDescription(request.getDescription()); // Ensure description is set
//        return candidat;
//    }
//
//}