package com.sales.app.entities;

import com.sales.app.nums.StatutVente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Vente")

public class Vente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long venteId;
        private Date dateVente;

        private StatutVente statutVente = StatutVente.EN_ATTENTE;
        private double total;

        @ManyToOne
        private Client client;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<LigneDeVente> ligneDeVente = new HashSet<>();

    @OneToOne(mappedBy = "vente", cascade = CascadeType.ALL)
    private Facture facture;

}
