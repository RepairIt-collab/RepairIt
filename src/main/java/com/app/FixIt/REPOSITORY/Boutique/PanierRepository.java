package com.app.FixIt.REPOSITORY.Boutique;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.ENTITIES.Boutique.Panier;
import com.app.FixIt.ENTITIES.Maintenance.Client;



public interface PanierRepository extends JpaRepository<Panier ,Long>{
    
    Panier findByClient(Client client);
}
