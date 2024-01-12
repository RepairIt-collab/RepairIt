package com.app.FixIt.BOUTIQUE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.BOUTIQUE.entities.Panier;


public interface PanierRepository extends JpaRepository<Panier ,Long>{
    Panier findByUserId(Long userId);
}
