package com.app.FixIt.CONTROLLER.Accueil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.FixIt.DTO.Maintenance.FullCalendar;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/RepairIt")
public class Calendar {

     @Autowired
    TachesRepository tachesRepository;
    

    @Autowired
    ClientRepository clientRepository;

     @Autowired
    EquipementsRepository equipementsRepository;
    
    @GetMapping("/Utilisateur/Calendrier")
    public String calendrier()
    {
        return "HTML/calendar";
    }

    @GetMapping("/Calendar")
    public ResponseEntity<List<FullCalendar>> Rechertache(HttpSession session){
        
        Long id = (Long) session.getAttribute("id");
        List<Taches> taches=null;
       // Equipements equipements=new Equipements();
        if(id != null){
            Optional<Client> optuser = clientRepository.findById(id);
            User user = optuser.get();;
            taches =tachesRepository.findAllByClient((Client)user);    
        }
        List<FullCalendar> events = new ArrayList<>();
        for (Taches task : taches) {
          //  equipements=task.getEquipements();
            FullCalendar event = new FullCalendar();
            //event.setNom(equipements.getNom());
            event.setDate(task.getDate());
            event.setType(task.getType());
            event.setEtat(task.getEtat());
            // Autres propriétés de l'événement, si nécessaire
    
            events.add(event);
        }
                  
         return ResponseEntity.ok(events);       
    }
}