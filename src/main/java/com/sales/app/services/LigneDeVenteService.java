package com.sales.app.services;

import com.sales.app.entities.LigneDeVente;

import java.util.List;

public interface LigneDeVenteService {
    List<LigneDeVente> getAllLignesDeVente();
    LigneDeVente getLigneDeVenteById(Long ligneDeVenteId);
    LigneDeVente saveLigneDeVente(LigneDeVente ligneDeVente);
    void deleteLigneDeVente(Long ligneDeVenteId);
}
