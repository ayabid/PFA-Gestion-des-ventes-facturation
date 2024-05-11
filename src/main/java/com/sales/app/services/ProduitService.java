package com.sales.app.services;

import com.sales.app.entities.Client;
import com.sales.app.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProduitService {
    List<Produit> findAllProduits();

    Produit findProduitById(Long id);

    void createProduit(Produit produit, byte[] imageData) throws IOException;

    void updateProduit(Produit produit, byte[] imageData) throws IOException;

    void deleteProduit(Long id);

    Page<Produit> findPaginated(Pageable pageable);

    List<Produit> searchProduits(String keyword);
}
