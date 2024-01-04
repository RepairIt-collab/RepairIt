package com.app.FixIt.BOUTIQUE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.app.FixIt.BOUTIQUE.entities.Produit;




@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

    Produit findByNom(String nom);

    static Produit findByName(String nom) {
        return null;
    }
    Produit findByPhotoAndEstcommande(String photo,Boolean estcommande);
    Produit findByPhotoAndOccupeAndEstcommande( String photo,Boolean occupe,Boolean estcommande);

    
}
