package com.app.FixIt.BOUTIQUE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.BOUTIQUE.entities.Domaine;



public interface DomaineRepository extends JpaRepository<Domaine, Long>{

    Domaine findByDomaine(String nom);
    
}
