package com.sales.app.services;

import com.sales.app.entities.Vente;

import java.util.List;

public interface VenteService {
    List<Vente> getAllVentes();
    Vente getVenteById(Long venteId);
    Vente saveVente(Vente vente);
    void deleteVente(Long venteId);
}
