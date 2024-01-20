package com.app.FixIt.REPOSITORY.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FixIt.ENTITIES.Maintenance.Client;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    Client  findFirstByUsernameAndPassword(String username, String password);
    List<Client> findByUsername(String username);
}
