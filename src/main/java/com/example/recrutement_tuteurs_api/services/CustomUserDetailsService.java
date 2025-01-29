package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.models.Admin;
import com.example.recrutement_tuteurs_api.models.Candidat;
import com.example.recrutement_tuteurs_api.models.CustomUserDetails;
import com.example.recrutement_tuteurs_api.repository.AdminRepository;
import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final CandidatRepository candidatRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, CandidatRepository candidatRepository) {
        this.adminRepository = adminRepository;
        this.candidatRepository = candidatRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username).orElse(null);
        if (admin != null) {
            return new CustomUserDetails(admin.getEmail(), admin.getPassword(), admin.getRole());
        }

        Candidat candidat = candidatRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));
        return new CustomUserDetails(candidat.getEmail(), candidat.getMotDePasse(), candidat.getRole());
    }
}