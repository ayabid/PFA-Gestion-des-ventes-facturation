package com.sales.app.services;

import com.sales.app.Repositories.VenteRepository;
import com.sales.app.entities.Facture;
import com.sales.app.entities.Vente;
import com.sales.app.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VenteServiceImpl implements VenteService{
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private FactureService factureService;


    @Override
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public Vente getVenteById(Long venteId) {
        return venteRepository.findById(venteId)
                .orElseThrow(() -> new NotFoundException("Vente not found with ID: " + venteId));
    }


    @Override
    @Transactional
    public Vente saveVente(Vente vente) {
        Vente savedVente = venteRepository.save(vente);

        // Créer une nouvelle facture pour cette vente
        Facture facture = new Facture();
        facture.setVente(savedVente);
        // D'autres champs de la facture peuvent être initialisés ici
        //factureService.saveFacture(facture);

        return savedVente;
    }

    @Override
    public void deleteVente(Long venteId) {
        venteRepository.deleteById(venteId);
    }
}

