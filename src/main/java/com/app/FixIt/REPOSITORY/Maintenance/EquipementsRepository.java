package com.app.FixIt.REPOSITORY.Maintenance;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;


@Repository
public interface EquipementsRepository extends JpaRepository<Equipements, Long>{
  List<Equipements> findByClient(Client client);
  Equipements findByClientAndNom(Client client,String nom);
}
