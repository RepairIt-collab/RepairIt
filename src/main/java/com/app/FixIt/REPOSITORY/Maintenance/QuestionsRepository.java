package com.app.FixIt.REPOSITORY.Maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long>{
    List<Questions> findByDomain(String domain);
}
