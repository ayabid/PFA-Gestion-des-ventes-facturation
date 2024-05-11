package com.sales.app.services;

import com.sales.app.Repositories.ProduitRepository;
import com.sales.app.entities.Produit;
import com.sales.app.exception.NotFoundException;

import com.sales.app.services.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit findProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produit not found with ID " + id));
    }

    @Override
    public void createProduit(Produit produit, byte[] imageData) throws IOException {
        if (imageData != null && imageData.length > 0) {
            produit.setImage(imageData);
        }

        produitRepository.save(produit);
    }

    @Override
    public void updateProduit(Produit produit, byte[] imageData) throws IOException {
        if (imageData != null && imageData.length > 0) {
            produit.setImage(imageData);
        } else {
            // Supprimer l'image si aucune image n'est fournie
            produit.setImage(null);
        }

        produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produit not found with ID " + id));

        produitRepository.delete(produit);
    }

    @Override
    public Page<Produit> findPaginated(Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    @Override
    public List<Produit> searchProduits(String keyword) {
        if (keyword != null) {
            return produitRepository.search(keyword);
        }
        return Collections.emptyList();
    }
}