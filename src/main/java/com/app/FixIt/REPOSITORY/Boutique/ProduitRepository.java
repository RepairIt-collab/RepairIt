package com.app.FixIt.REPOSITORY.Boutique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Boutique.Produit;


@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

    Produit findByNom(String nom);


    
}