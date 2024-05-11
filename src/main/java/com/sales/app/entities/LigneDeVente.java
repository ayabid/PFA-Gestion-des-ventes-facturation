package com.sales.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name="LigneDeVente")

public class LigneDeVente {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long ligneDeVenteId;

        private int quantite;
        private double prixUnitaire;


        @ManyToOne
        @JoinColumn(name = "vente_id")
        private Vente vente;

        @ManyToOne
        @JoinColumn(name = "produit_id")
        private Produit produit;


    }
