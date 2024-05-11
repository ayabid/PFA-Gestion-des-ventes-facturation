package com.sales.app.services;

import com.sales.app.Repositories.LigneDeVenteRepository;
import com.sales.app.entities.LigneDeVente;
import com.sales.app.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LigneDeVenteServiceImpl implements LigneDeVenteService{
    @Autowired
    private LigneDeVenteRepository ligneDeVenteRepository;

    @Override
    public List<LigneDeVente> getAllLignesDeVente() {
        return ligneDeVenteRepository.findAll();
    }

    @Override
    public LigneDeVente getLigneDeVenteById(Long ligneDeVenteId) {
        return ligneDeVenteRepository.findById(ligneDeVenteId)
                .orElseThrow(() -> new NotFoundException("Ligne de vente not found with ID: " + ligneDeVenteId));
    }

    @Override
    public LigneDeVente saveLigneDeVente(LigneDeVente ligneDeVente) {
        return ligneDeVenteRepository.save(ligneDeVente);
    }

    @Override
    public void deleteLigneDeVente(Long ligneDeVenteId) {
        ligneDeVenteRepository.deleteById(ligneDeVenteId);
    }
}
