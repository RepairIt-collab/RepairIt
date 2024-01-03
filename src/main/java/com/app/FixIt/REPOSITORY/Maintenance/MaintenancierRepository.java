package com.app.FixIt.REPOSITORY.Maintenance;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import jakarta.persistence.*;
// import javax.persistence.*;
// javax.persistence
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;

import java.util.List;

@Repository
public interface MaintenancierRepository extends JpaRepository<Maintenancier, Long> {
    List<Maintenancier> findByStatus(Boolean status);

    Maintenancier findFirstByUsernameAndPassword(String username, String password);

    Maintenancier findFirstByUsername(String username);

    List<Maintenancier> findByStatusAndSpecialite(Boolean status, String specialite);

    List<Maintenancier> findByStatusAndSpecialiteAndTest(Boolean status, String specialite, Integer test);

    List<Maintenancier> findByTest(int test);

    List<Maintenancier> findByTestAndSpecialite(int test, String specialite);

    @Query("SELECT m FROM Maintenancier m WHERE :idfilleuls IN (m.idfilleuls)")
    List<Maintenancier> findByFilleuls(@Param("idfilleuls") Long idfilleuls);

    // @Query("SELECT m FROM Maintenancier m WHERE :idFilleul MEMBER OF m.idfilleuls")
    // Maintenancier findByIdfilleuls(Long idFilleul);
    // @Query(value = "SELECT m.* FROM Maintenancier m INNER JOIN unnest(m.idfilleuls) f(idfilleul) ON f.idfilleul = :idFilleul", nativeQuery = true)
    // Maintenancier findByFilleulId(Long idFilleul);
    // @Query(value = "SELECT m FROM Maintenancier m JOIN unnest(m.idfilleuls) f(idfilleul) WHERE f.idfilleul = :idFilleul", nativeQuery = true)
    // // Maintenancier findByFilleulId(@Param("idFilleul") Long idFilleul);
    // @Query(value = "SELECT m FROM Maintenancier m WHERE :idFilleul IN (SELECT f FROM unnest(m.idfilleuls) f)", nativeQuery = true)
    // Maintenancier findByFilleulId(@Param("idFilleul") Long idFilleul);
    // "SELECT m FROM Maintenancier m WHERE :idFilleul IN (SELECT f FROM unnest(m.idfilleuls) f)"
//     SELECT m.* FROM Maintenancier m 
// INNER JOIN unnest(m.idfilleuls) f(idfilleul) ON f.idfilleul = :idFilleul
    // SELECT * FROM Maintenancier m WHERE ? = ANY (SELECT unnest(idfilleuls) FROM Maintenancier WHERE m.id = id)

    // Maintenancier findByIdfilleulsContaining(Long idfilleuls);
    // Maintenancier findByIdfilleulsContaining(Long idfilleuls);
}
