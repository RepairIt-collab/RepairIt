package com.app.FixIt.STATISTIQUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;

@Controller
public class Stat {

  @Autowired
  ClientRepository clientRepository;

  @GetMapping("/loadClient")
  public ResponseEntity<List<Client>>loadclient()
  {
       List<Client> client=null;
       client=clientRepository.findAll();

       return ResponseEntity.ok(client);
  }

    
}
