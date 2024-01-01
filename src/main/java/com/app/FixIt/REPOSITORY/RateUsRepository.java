package com.app.FixIt.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.FixIt.ENTITIES.Maintenance.RateUs;

public interface RateUsRepository extends JpaRepository<RateUs,Long> {
    
}
