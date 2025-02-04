package com.example.recrutement_tuteurs_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Configuration de CORS pour autoriser les requ�tes depuis le port 3000
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Permet de d�clarer les origines autoris�es pour les requ�tes CORS
        return new WebMvcConfigurer() {

            // Permet de d�clarer les origines autoris�es pour les requ�tes CORS et les m�thodes HTTP autoris�es
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // Configuration des origines autoris�es pour les requ�tes CORS et les m�thodes HTTP autoris�es
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        // Remplacez par les m�thodes HTTP autoris�es
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Remplacez par les headers autoris�s
                        .allowedHeaders("*")
                        // Remplacez par les headers expos�s
                        .allowCredentials(true);
            }
        };
    }
}
