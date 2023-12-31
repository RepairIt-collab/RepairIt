package com.app.FixIt.REPOSITORY.Boutique;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.ENTITIES.Boutique.Commande;
import com.app.FixIt.ENTITIES.Maintenance.Client;




public interface CommandeRepository extends JpaRepository<Commande , Long>{
 public Commande findByclient(Client client);   
}
