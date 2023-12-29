package com.app.FixIt.CONTROLLER.Maintenance;


import java.util.Base64;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.FixIt.DTO.Maintenance.TacheDto;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.NotificationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;
import com.app.FixIt.SERVICE.Maintenance.EquipementsService;
import com.app.FixIt.SERVICE.Maintenance.MaintenancierService;
import com.app.FixIt.SERVICE.Maintenance.TacheService;
import com.app.FixIt.Websocket.WebSocketController;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/RepairIt/Client/Maintenance")
public class Tache{


    @Autowired
    private TachesRepository tachesRepository;
    @Autowired
    private EquipementsRepository equipementsRepository;

    @Autowired
    MaintenancierRepository maintenancierRepository;
    @Autowired
    Maintenance mainController;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TacheService tachesService;

    @Autowired
    MaintenancierService maintenancierService;

    @Autowired
    WebSocketController webSocketController;

    @Autowired
    NotificationRepository notificationRepository;

    /**
     * Creer une tache en base de donnees
     * Apres l'avoir recu du front
     * etat: non complete il manque l'id dans les parametres de la requete
     */
    
    @PostMapping("/CreerTache")
    @ResponseBody
    public Notification creerTache(@RequestBody TacheDto todoTache,HttpSession session){

        Long id = (Long) session.getAttribute("id");
        Client client = clientRepository.findById(id).orElse(null);
       TacheService tacheService = new TacheService(tachesRepository);
       Taches tache  = new Taches();       
        System.out.println(todoTache.getLatitude());
       tache.setDate(todoTache.getDate());
       tache.setType(todoTache.getType());
       System.out.println(todoTache.getType().value());
       tache.setDescription(todoTache.getDescription());
       tache.setEtat(0);
       tache.setLongitude(todoTache.getLongitude());
       tache.setLatitude(todoTache.getLatitude());
       tache.setClient(client);
       tache.setImage(todoTache.getPhoto());
    // //=============================================Just to test the chat
    //    Maintenancier maintenancierChat =  maintenancierRepository.findById(93L).get();
    //    tache.setMaintenancier(maintenancierChat);
    // //==================================================================
       tache = tacheService.saveTache(tache);
       tache = tacheService.saveTache(tache);
    //    if(tache.getImage()!= null){
    //    String base64Data = Base64.getEncoder().encodeToString(tache.getImage());
            
    //         tache.setImgString(base64Data);

    //     }
       todoTache.setId1(tache.getId());

       EquipementsService equipementsService=new EquipementsService(equipementsRepository);
       Equipements equipements=new Equipements();

       equipements.setPhoto(todoTache.getPhoto());
       equipements.setNom(todoTache.getNom());
       equipements.setEtats(0);

       equipements=equipementsService.saveEquipements(equipements);

       
        List<Maintenancier> maintenanciers = maintenancierService.findMaintenancier(tache);
        System.out.println("------------"+maintenanciers);
        Notification notif = new Notification();
        notif.setMaintenanciers(maintenanciers);
        String messageString  = "Taches concernant un "+equipements.getNom() +" de type "+ tache.getType()+" Description du Probleme " + tache.getDescription()+" a realiser au plus tard " + tache.getDate() ;//+ "//"+tache.getId();
        notif.setMessage(messageString);
        notif.setTaches(tache);
        notificationRepository.save(notif);
        
    return notif;
    }
    
    @PostMapping("/SuprimerTache/{id}")
    public void DeleteTache(@PathVariable Long id) {
        Taches taches = tachesRepository.findById(id).orElse(null);
        Notification notification = notificationRepository.findByTaches(taches);
        notificationRepository.delete(notification);
        // TacheService tacheService = new TacheService(tachesRepository);
        // tacheService.DeleteTache(id);
        tachesRepository.delete(taches);
        

    }

    @GetMapping("/details")
    public String details(Model model ,HttpSession session){

        Long id = (Long) session.getAttribute("id");
        Client client = clientRepository.findById(id).orElse(null);
        List<Taches> tachess = tachesRepository.findByClient(client);
        Iterable<Taches> taches = tachess;
        for(Taches tacher : taches){
        String base64Data = Base64.getEncoder().encodeToString(tacher.getImage());
            if(tacher.getImage()!= null){
            tacher.setImgString(base64Data);

        }}
        
        model.addAttribute("taches", taches);
        
        return mainController.Client(model,session);
        
    }

    @PostMapping("/validerTache")
    public void validerTache(@RequestParam("idNotif") Long idNotif, @RequestParam("idM") Long idM){
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        List<Maintenancier> main = notification.getMaintenanciers();
        Maintenancier maintenancier = maintenancierRepository.findById(idM).orElse(null);
        for(Maintenancier mainmain : main){
            if(mainmain.getId() == maintenancier.getId()){
                main.remove(maintenancier);
                
            }
        }
        // Maintenancier
        notification.setIdMaintenancier(idM); 
        notificationRepository.save(notification);
    }

    @PostMapping("/soumettrePrix")
    public void soumettrePrix(@RequestParam("prix") Integer prix){
        //LOGIQUE DU CODE POUR LE PAYEMENT

    }



    


   
}

