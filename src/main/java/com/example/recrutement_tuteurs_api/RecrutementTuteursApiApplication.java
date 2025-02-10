package com.example.recrutement_tuteurs_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //  Activation audit automatique pour CreatedDate et LastModifiedDate
public class RecrutementTuteursApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecrutementTuteursApiApplication.class, args);
	}

}
