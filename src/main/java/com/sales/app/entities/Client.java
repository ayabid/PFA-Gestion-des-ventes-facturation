package com.sales.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Client")
public class Client {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "CIN")
    private String cin;

    @Column(name = "Adrresse")
    private String adresse;
    @Column(name = "Email")
    private String email;
    @Column(name = "Télé")
    private String telephone;

    @OneToMany(mappedBy = "client")
    private List<Vente> ventes;
}
