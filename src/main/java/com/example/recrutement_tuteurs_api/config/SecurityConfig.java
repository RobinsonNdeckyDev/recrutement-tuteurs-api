package com.example.recrutement_tuteurs_api.config;

import com.example.recrutement_tuteurs_api.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Ajout correct de CORS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/swagger-ui/**", "/v3/api-docs/**", "/api/auth/**").permitAll()
                    // Routes accessibles uniquement par l'admin
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")

                    // Routes accessibles par les deux rôles
                    .requestMatchers("/api/common/**").hasAnyRole("ADMIN", "CANDIDAT")

                    // Routes avec des permissions spécifiques (lecture, modification, suppression)

                    // Lecture
                    .requestMatchers(HttpMethod.GET, "/api/resources/**").hasAnyRole("ADMIN", "CANDIDAT")

                    // Création
                    .requestMatchers(HttpMethod.POST, "/api/resources/**").hasRole("ADMIN")

                    // Modification
                    .requestMatchers(HttpMethod.PUT, "/api/resources/**").hasRole("ADMIN")

                    // Suppression
                    .requestMatchers(HttpMethod.DELETE, "/api/resources/**").hasRole("ADMIN")

                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));  // Autorise uniquement l'origine http://localhost:4200
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Autorise les méthodes HTTP spécifiées
        configuration.setAllowedHeaders(List.of("*"));  // Autorise tous les en-têtes
        configuration.setAllowCredentials(true);  // Autorise les cookies et les en-têtes d'autorisation

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Applique cette configuration à tous les chemins
        return source;
    }
}