package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.DTO.Mail.EmailDTO;
import com.app.FixIt.DTO.Maintenance.MaintenancierDTO;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;


@RestController
public class MaintenancierController {
    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    TachesRepository tachesRepository;

    @PostMapping("/update-location")
    public void update_location(@RequestBody MaintenancierDTO user) {
        Long id = user.getTelephone().longValue();
        Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
        if (maintenancier != null) {
            maintenancier.setlatitude(user.getlatitude());
            maintenancier.setLongitude(user.getLongitude());
            maintenancierRepository.save(maintenancier);
            System.out.println("===================donnees recu pour la modification des coordonnes");
        }
    }

    @PostMapping("/formation")
    public void formation() {

    }

    @PostMapping("/afficherFilleul")
    public List<Maintenancier> sendFilleul(@RequestParam("id") Long id) {
        Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
        List<Long> listIdFilleuls = maintenancier.getIdfilleuls();

        List<Maintenancier> listMaintenanciers = new ArrayList<>();
        if (listIdFilleuls != null) {
            for (Long idF : listIdFilleuls) {
                Maintenancier main = maintenancierRepository.findById(idF).orElse(null);
                listMaintenanciers.add(main);
            }

        }

        return listMaintenanciers;

    }

    @PostMapping("/newNotif")
    public Notification postMethodName(@RequestBody Notification entity) {
        //TODO: process POST request
        
        return entity;
    }

        @PostMapping("/soumettrePrix")
    public EmailDTO soumettrePrix(@RequestParam("idT") Long idT,@RequestParam("message") String mess) {
        // LOGIQUE DU CODE POUR LE PAYEMENT
        // ENVOIE DE L'EMAIL AU CLIENT POUR LA VALIDATION DU PAIEMENT
        System.out.println("message--------"+mess);
        Taches tache = tachesRepository.findById(idT).orElse(null);
        Client client = tache.getClient();
        String email = client.getEmail();
        String subjet = "Validation du paiement";
        EmailDTO emailDTO = new EmailDTO(email, subjet, mess);
        return emailDTO;

    }


    //TERMINER UNE TACHE
    @PostMapping("/terminerTache")
    public void terminerTache(@RequestParam("idT") Long idT, @RequestParam("idM") Long idM,@RequestParam("prix") Integer prix) {
        Maintenancier maintenancier = maintenancierRepository.findById(idM).orElse(null);
        maintenancier.setStatus(true);
        Taches taches = tachesRepository.findById(idT).orElse(null);
        taches.setEtat(2);
        taches.setCout(prix);
        if(maintenancier.getTaches() == null){
            List<Taches> taches2 = new ArrayList<>();
            taches2.add(taches);
            maintenancier.setTaches(taches2);
        } else{
            maintenancier.getTaches().add(taches);
        }
        tachesRepository.save(taches);
        maintenancierRepository.save(maintenancier);
        List<Long> longs = maintenancier.getIdfilleuls();
        if(longs != null){
            for(Long elt : longs){
                Maintenancier mainF = maintenancierRepository.findById(elt).orElse(null);
                if(mainF.getStatus() == false){
                    mainF.setStatus(true);
                    maintenancierRepository.save(mainF);
                }
            }
        }

        // TODO: process POST request

        // return entity;
    }
    
}
