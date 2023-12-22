package com.app.FixIt.REPOSITORY.Maintenance;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {


    List<Evaluation> findByDomain(String domain);
    Evaluation findFirstByDomain(String domain);
    Evaluation findFirstByDomainAndDateGreaterThanOrderByDateAsc(String domain, LocalDate currentDate);
    Evaluation findByMaintenanciers(Maintenancier maintenancier);

}
