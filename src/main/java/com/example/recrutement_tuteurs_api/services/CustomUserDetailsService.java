package com.example.recrutement_tuteurs_api.services;

import com.example.recrutement_tuteurs_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}
















//// CustomUserDetailsService.java
//package com.example.recrutement_tuteurs_api.services;
//
//import com.example.recrutement_tuteurs_api.models.Admin;
//import com.example.recrutement_tuteurs_api.models.Candidat;
//import com.example.recrutement_tuteurs_api.models.CustomUserDetails;
//import com.example.recrutement_tuteurs_api.models.User;
//import com.example.recrutement_tuteurs_api.repository.AdminRepository;
//import com.example.recrutement_tuteurs_api.repository.CandidatRepository;
//import com.example.recrutement_tuteurs_api.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final AdminRepository adminRepository;
//    private final CandidatRepository candidatRepository;
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(AdminRepository adminRepository, CandidatRepository candidatRepository, UserRepository userRepository) {
//        this.adminRepository = adminRepository;
//        this.candidatRepository = candidatRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByEmail(username).orElse(null);
//        if (admin != null) {
//            return new CustomUserDetails(admin.getEmail(), admin.getPassword(), admin.getRole());
//        }
//
//        Candidat candidat = candidatRepository.findByEmail(username).orElse(null);
//        if (candidat != null) {
//            return new CustomUserDetails(candidat.getEmail(), candidat.getPassword(), candidat.getRole());
//        }
//
//        User user = userRepository.findByEmail(username).orElseThrow(() ->
//                new UsernameNotFoundException("User not found with username: " + username));
//        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRole());
//    }
//}