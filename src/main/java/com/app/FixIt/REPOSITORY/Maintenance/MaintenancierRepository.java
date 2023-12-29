package com.app.FixIt.REPOSITORY.Maintenance;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;

import java.util.List;

@Repository
public interface MaintenancierRepository extends JpaRepository<Maintenancier, Long>{
    List<Maintenancier> findByStatus(Boolean status);
    Maintenancier findFirstByUsernameAndPassword(String username, String password);
    Maintenancier findFirstByUsername(String username);
    List<Maintenancier> findByStatusAndSpecialite(Boolean status, String specialite);
    List<Maintenancier> findByStatusAndSpecialiteAndTest(Boolean status, String specialite, Integer test);
    List<Maintenancier> findByTest(int test);
    List<Maintenancier> findByTestAndSpecialite(int test, String specialite);
    @Query("SELECT m FROM Maintenancier m WHERE :idfilleuls IN (m.idfilleuls)")
    List<Maintenancier> findByFilleuls(@Param("idfilleuls") Long idfilleuls);
}
