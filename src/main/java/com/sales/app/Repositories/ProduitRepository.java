package com.sales.app.Repositories;
import com.sales.app.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("SELECT p FROM Produit p WHERE lower(p.nom) LIKE lower(concat('%', ?1, '%'))")
    List<Produit> search(String keyword);
}