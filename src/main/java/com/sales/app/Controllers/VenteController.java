package com.sales.app.Controllers;

import com.sales.app.entities.Vente;
import com.sales.app.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class VenteController {
    @Autowired
    private VenteService venteService;

    @GetMapping("/ventes")
    public List<Vente> getAllVentes() {
        return venteService.getAllVentes();
    }

    @GetMapping("/vente/{venteId}")
    public Vente getVenteById(@PathVariable Long venteId) {
        return venteService.getVenteById(venteId);
    }

    @PostMapping("/add-vente")
    public Vente addVente(@RequestBody Vente vente) {
        return venteService.saveVente(vente);
    }

    @DeleteMapping("/remove-vente/{venteId}")
    public void deleteVente(@PathVariable Long venteId) {
        venteService.deleteVente(venteId);
    }
}