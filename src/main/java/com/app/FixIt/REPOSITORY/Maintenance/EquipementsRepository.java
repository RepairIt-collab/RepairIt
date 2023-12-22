package com.app.FixIt.REPOSITORY.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Equipements;

@Repository
public interface EquipementsRepository extends JpaRepository<Equipements, Long>{
    
}
