package com.app.FixIt.CONTROLLER.Accueil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.DTO.Maintenance.FullCalendar;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;
import com.app.FixIt.REPOSITORY.User.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/RepairIt")
public class Calendar {

     @Autowired
    TachesRepository tachesRepository;
    

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
   MaintenancierRepository maintenancierRepository;

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
        if(id != null){
            if (clientRepository.findById(id).isPresent()) {
                Optional<Client> optClientUser = clientRepository.findById(id);
                if (optClientUser.isPresent()) {
                    User user = optClientUser.get();
                    taches = tachesRepository.findAllByClient((Client) user);
                }
            }
            
            if (maintenancierRepository.findById(id).isPresent()) {
                Optional<Maintenancier> optMaintenancierUser = maintenancierRepository.findById(id);
                if (optMaintenancierUser.isPresent()) {
                    User user = optMaintenancierUser.get();
                    taches = tachesRepository.findAllByMaintenancier((Maintenancier) user);
                }
            }             
        }
        List<FullCalendar> events = new ArrayList<>();
        for (Taches task : taches) {
            //Equipements equipements=task.getEquipements();
            FullCalendar event = new FullCalendar();
          //  event.setNom(equipements.getNom());
            event.setDate(task.getDate());
            event.setType(task.getType());
            if(task.getEtat()==0)
            {
                event.setEtat("Nouvelle tache");
            }
             if(task.getEtat()==1)
            {
                event.setEtat("Tache en cours");
            }
            if(task.getEtat()==2)
            {
                event.setEtat("Tache terminee");
            }
           
            event.setDescription(task.getDescription());
            // Autres propriétés de l'événement, si nécessaire
    
            events.add(event);
        }
                  
         return ResponseEntity.ok(events);       
    }
}