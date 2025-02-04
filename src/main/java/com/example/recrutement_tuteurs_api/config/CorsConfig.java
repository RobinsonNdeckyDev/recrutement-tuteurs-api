package com.example.recrutement_tuteurs_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Configuration de CORS pour autoriser les requêtes depuis le port 3000
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Permet de déclarer les origines autorisées pour les requêtes CORS
        return new WebMvcConfigurer() {

            // Permet de déclarer les origines autorisées pour les requêtes CORS et les méthodes HTTP autorisées
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // Configuration des origines autorisées pour les requêtes CORS et les méthodes HTTP autorisées
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        // Remplacez par les méthodes HTTP autorisées
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Remplacez par les headers autorisés
                        .allowedHeaders("*")
                        // Remplacez par les headers exposés
                        .allowCredentials(true);
            }
        };
    }
}
