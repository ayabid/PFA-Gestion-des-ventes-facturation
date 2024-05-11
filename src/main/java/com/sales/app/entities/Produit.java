package com.sales.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Produit")

public class Produit {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "Nom")
        private String nom;
        @Column(name = "Description")
        private String description;
        @Column(name = "Prix")
        private double prix;
        @Column(name = "QStock")
        private int qStock ;
        @Lob
        @NotNull
        private byte[] image;

        @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL , orphanRemoval = true)
        private Set<LigneDeVente> ligneDeVente = new HashSet<>();
}
