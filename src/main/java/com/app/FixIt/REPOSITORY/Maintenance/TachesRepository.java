package com.app.FixIt.REPOSITORY.Maintenance;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.Type;
import com.app.FixIt.ENTITIES.Maintenance.User;


@Repository
public interface TachesRepository extends JpaRepository<Taches, Long>{
    List<Taches> findByClient(Client client);

    List<Taches> findAllByClient(Client user);

    List<Taches> findAllByMaintenancier(Maintenancier user);

    List<Taches> findByEtatAndType(Integer etat, Type type);
}
