package com.sales.app.Controllers;

import com.sales.app.entities.LigneDeVente;
import com.sales.app.services.LigneDeVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class LigneDeVenteController {
    @Autowired
    private LigneDeVenteService ligneDeVenteService;

    @GetMapping("/lignesdevente")
    public List<LigneDeVente> getAllLignesDeVente() {
        return ligneDeVenteService.getAllLignesDeVente();
    }

    @GetMapping("lignedevente/{ligneDeVenteId}")
    public LigneDeVente getLigneDeVenteById(@PathVariable Long ligneDeVenteId) {
        return ligneDeVenteService.getLigneDeVenteById(ligneDeVenteId);
    }

    @PostMapping("/add-lignedevente")
    public LigneDeVente addLigneDeVente(@RequestBody LigneDeVente ligneDeVente) {
        return ligneDeVenteService.saveLigneDeVente(ligneDeVente);
    }

    @DeleteMapping("/remove-lignedevente/{ligneDeVenteId}")
    public void deleteLigneDeVente(@PathVariable Long ligneDeVenteId) {
        ligneDeVenteService.deleteLigneDeVente(ligneDeVenteId);
    }
}