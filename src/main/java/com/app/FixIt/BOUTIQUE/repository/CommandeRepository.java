package com.app.FixIt.BOUTIQUE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.BOUTIQUE.entities.Commande;


public interface CommandeRepository extends JpaRepository<Commande ,Long>{
    Commande findByUserId(Long userId);
}
