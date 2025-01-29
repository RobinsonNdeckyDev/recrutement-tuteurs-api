package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.*;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import com.example.recrutement_tuteurs_api.repository.UserRepository;
import com.example.recrutement_tuteurs_api.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CandidatRepository candidatRepository;
    private final PasswordEncoder passwordEncoder; // Pour encoder le mot de passe

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            UserRepository userRepository,
            AdminRepository adminRepository,
            CandidatRepository candidatRepository,
            PasswordEncoder passwordEncoder
            ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.candidatRepository = candidatRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException ("L'utilisateur existe d�j�");
        }

        try {
            // Encodage du mot de passe
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            // Cr�ation de l'utilisateur
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(encodedPassword);
            user.setRole(request.getRole());
            userRepository.save(user);

            // Ajout dans la table correspondante
            if (Role.ADMIN.equals(request.getRole())) {
                Admin admin = new Admin();
                admin.setUser(user);
                admin.setPrenom(request.getPrenom());
                admin.setNom(request.getNom());
                admin.setEmail(request.getEmail());
                admin.setMotDePasse(encodedPassword);
                admin.setPhotoProfil(request.getPhotoProfil());
                admin.setAdresse(request.getAdresse());
                admin.setTelephone(request.getTelephone());
                adminRepository.save(admin);
            } else if (Role.CANDIDAT.equals(request.getRole())) {
                Candidat candidat = getCandidat(request, user, encodedPassword);
                candidatRepository.save(candidat);
            }

            // G�n�ration du JWT
            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            return new AuthenticationResponse(jwt);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'enregistrement de l'utilisateur: " + e.getMessage());
        }
    }

    private static Candidat getCandidat(RegisterRequest request, User user, String encodedPassword) {
        Candidat candidat = new Candidat();
        candidat.setUser(user);
        candidat.setPrenom(request.getPrenom());
        candidat.setNom(request.getNom());
        candidat.setEmail(request.getEmail());
        candidat.setMotDePasse(encodedPassword);
        candidat.setAdresse(request.getAdresse());
        candidat.setTelephone(request.getTelephone());
        candidat.setDescription(request.getDescription());
        candidat.setPhotoProfil(request.getPhotoProfil());
        return candidat;
    }


}