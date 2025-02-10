// Candidat.java
package com.example.recrutement_tuteurs_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidats")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Candidat extends User {

    @Column(nullable = true)
    private String cvUrl;

    @Column(nullable = true)
    private String siteUrl;

    @Column(nullable = true)
    private String niveauEtude;

    @Column(nullable = true)
    private String domaineEtude;

}