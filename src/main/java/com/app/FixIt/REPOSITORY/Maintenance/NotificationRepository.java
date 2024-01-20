package com.app.FixIt.REPOSITORY.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Taches;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification , Long>{
    // List<Notification> findByMaintenanciers(List<Maintenancier> maintenanciers);
    List<Notification> findByMaintenanciers(Maintenancier maintenancier);
    List<Notification> findByIdMaintenancier(Long idMaintenancier);
    Notification findByTaches(Taches taches);
}
