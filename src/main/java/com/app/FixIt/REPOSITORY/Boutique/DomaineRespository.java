package com.app.FixIt.REPOSITORY.Boutique;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.ENTITIES.Boutique.Domaine;



public interface DomaineRespository extends JpaRepository<Domaine ,Long>{
    Domaine findByNom(String nom) ;
}
