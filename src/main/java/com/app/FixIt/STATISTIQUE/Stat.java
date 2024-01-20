package com.app.FixIt.STATISTIQUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/RepairIt")
public class Stat {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  TachesRepository tachesRepository;
  
  @Autowired
  MaintenancierRepository maintenancierRepository;

  @GetMapping("/Statistique/Client")
  public String statcli() {
      return "HTML/stat";
  }

  @GetMapping("/stat")
  public ResponseEntity<List<Integer>> statclient(HttpSession session) {
      Long id = (Long) session.getAttribute("id");
      List<Integer> liste = new ArrayList<>();

      Optional<Client> optClientUser = clientRepository.findById(id);
      if (optClientUser.isPresent()) {
          Client client = optClientUser.get();
          
          int j = 0, k = 0, l = 0;
          // Initialisation de tous les éléments à zéro
          for (int i = 0; i < 3; i++) {
              liste.add(i, 0);
          }

          List<Taches> taches = tachesRepository.findAllByClient(client);

          for (Taches task : taches) {
              if (task.getEtat() == 0) {
                  j++;
                  liste.set(0, j);
              } else if (task.getEtat() == 1) {
                  k++;
                  liste.set(1, k);
              } else if (task.getEtat() == 2) {
                  l++;
                  liste.set(2, l);
              }
          }

      }

      Optional<Maintenancier> optMaintenancierUser = maintenancierRepository.findById(id);
      if (optMaintenancierUser.isPresent()) {
          Maintenancier maintenancier = optMaintenancierUser.get();
          int j = 0, k = 0, l = 0;
          // Initialisation de tous les éléments à zéro
          for (int i = 0; i < 3; i++) {
              liste.add(i, 0);
          }

          List<Taches> taches = tachesRepository.findAllByMaintenancier(maintenancier);

          for (Taches task : taches) {
              if (task.getEtat() == 0) {
                  j++;
                  liste.set(0, j);
              } else if (task.getEtat() == 1) {
                  k++;
                  liste.set(1, k);
              } else if (task.getEtat() == 2) {
                  l++;
                  liste.set(2, l);
              }
          }

          
      }

      return ResponseEntity.ok(liste);
  }

  @GetMapping("/stat2")
  public ResponseEntity<List<Integer>> statclient2(HttpSession session) {
      Long id = (Long) session.getAttribute("id");
      List<Integer> liste = new ArrayList<>();
      Optional<Client> optClientUser = clientRepository.findById(id);
      if (optClientUser.isPresent()) {
          Client client = optClientUser.get();
         
          List<Integer> indice = new ArrayList<>();
          // Initialisation de tous les éléments à zéro
          for (int i = 0; i < 12; i++) {
              liste.add(i, 0);
              indice.add(i, 0);
          }

          List<Taches> taches = tachesRepository.findAllByClient(client);

          for (Taches task : taches) {
              if (task.getEtat() == 0) {
                  int month = task.getDate().getMonthValue();
                  indice.set(month - 1, indice.get(month - 1) + 1);
                  liste.set(month - 1, indice.get(month - 1));
              }
          }

      }

      Optional<Maintenancier> optMaintenancierUser = maintenancierRepository.findById(id);
      if (optMaintenancierUser.isPresent()) {
          Maintenancier maintenancier = optMaintenancierUser.get();
          List<Integer> indice = new ArrayList<>();
          // Initialisation de tous les éléments à zéro
          for (int i = 0; i < 12; i++) {
              liste.add(i, 0);
              indice.add(i, 0);
          }
          List<Taches> taches = tachesRepository.findAllByMaintenancier(maintenancier);

          for (Taches task : taches) {
              if (task.getEtat() == 0) {
                  int month = task.getDate().getMonthValue();
                  indice.set(month - 1, indice.get(month - 1) + 1);
                  liste.set(month - 1, indice.get(month - 1));
              }
          }

        
      }

      return ResponseEntity.ok(liste);
    }

     
}