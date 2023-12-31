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
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/RepairIt")
public class Stat {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  TachesRepository tachesRepository;


  @GetMapping("/Statistique/Client")
  public String statcli()
  {
      return "HTML/stat";
  }


  @GetMapping("/statclient")
  public ResponseEntity<List<Integer>>statclient(HttpSession session)
  {
       List<Integer> liste = new ArrayList<>();
       int j=0,k=0,l=0;
      // Initialisation de tous les éléments à zéro
      for (int i = 0; i < 3; i++) {
          liste.add(i, 0);
      }

      Long id = (Long) session.getAttribute("id");
      List<Taches> taches=null;
      Optional<Client> optClientUser = clientRepository.findById(id);
      User user = optClientUser.get();
      taches = tachesRepository.findAllByClient((Client) user);

      for (Taches task : taches) {
        if(task.getEtat()==0)
        {
            j=j+1;
            liste.set(0,j);
        }
        if(task.getEtat()==1)
        {
            k=k+1;
            liste.set(1,k);
        }
        if(task.getEtat()==2)
        {
            l=l+1;
            liste.set(2,l);
        }
      }

      return ResponseEntity.ok(liste);      
  }

   @GetMapping("/statclient2")
  public ResponseEntity<List<Integer>>statclient2(HttpSession session)
  {
       List<Integer> liste = new ArrayList<>();
       List<Integer> indice = new ArrayList<>();
      // Initialisation de tous les éléments à zéro
      for (int i = 0; i < 12; i++) {
          liste.add(i, 0);
      }
      for (int i = 0; i < 12; i++) {
          indice.add(i, 0);
      }

      Long id = (Long) session.getAttribute("id");
      List<Taches> taches=null;
      Optional<Client> optClientUser = clientRepository.findById(id);
      User user = optClientUser.get();
      taches = tachesRepository.findAllByClient((Client) user);

      for (Taches task : taches) {
        if(task.getEtat()==0)
        {
            for (int i = 0; i < 12; i++) {
                  if(task.getDate().getMonthValue()==i+1){
                     indice.set(i,indice.get(i)+1);
                     liste.set(i,indice.get(i));
               }
            }
        
        }
      }

        for (int i = 0; i < 12; i++) {
         System.out.print(liste.get(i));
      }

      return ResponseEntity.ok(liste);      
  }



    
}
