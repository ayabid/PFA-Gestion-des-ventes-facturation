package com.sales.app.Repositories;

import com.sales.app.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.nom LIKE %?1%" +
            " OR c.cin LIKE %?1%" +
            " OR c.adresse LIKE %?1%" +
            " OR c.email LIKE %?1%")
    List<Client> search(String keyword);
}