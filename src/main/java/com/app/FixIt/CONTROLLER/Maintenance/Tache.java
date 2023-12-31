package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.Base64;
import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.FixIt.DTO.Mail.EmailDTO;
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
public class Tache {

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
    public Notification creerTache(@RequestBody TacheDto todoTache, HttpSession session) {

        Long id = (Long) session.getAttribute("id");
        Client client = clientRepository.findById(id).orElse(null);
        TacheService tacheService = new TacheService(tachesRepository);
        LocalDate localDate = LocalDate.now();
        Taches tache = new Taches();
        System.out.println(todoTache.getLatitude());
        tache.setDate(localDate);
        tache.setType(todoTache.getType());
        System.out.println(todoTache.getType().value());
        tache.setDescription(todoTache.getDescription());
        tache.setEtat(0);
        tache.setLongitude(todoTache.getLongitude());
        tache.setLatitude(todoTache.getLatitude());
        tache.setClient(client);
        tache.setImage(todoTache.getPhoto());
        if (todoTache.getNom() != null) {
            Long idE = Long.parseLong(todoTache.getNom());
            Equipements equipement = equipementsRepository.findById(idE).orElse(null);
            tache.setEquipements(equipement);
            equipement.setEtats(1);
            equipementsRepository.save(equipement);
        }
        tache = tacheService.saveTache(tache);
        todoTache.setId1(tache.getId());

        List<Maintenancier> maintenanciers = maintenancierService.findMaintenancier(tache);
        // List<Maintenancier> maintenanciersfilleuls =
        System.out.println("------------" + maintenanciers);
        Notification notif = new Notification();
        notif.setMaintenanciers(maintenanciers);
        String messageString = "Taches concernant un equipement de type " + tache.getType()
                + " Description du Probleme " + tache.getDescription();
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
        tachesRepository.delete(taches);

    }

    @GetMapping("/details")
    public String details(Model model, HttpSession session) {

        Long id = (Long) session.getAttribute("id");
        Client client = clientRepository.findById(id).orElse(null);
        List<Taches> tachess = tachesRepository.findByClient(client);
        Iterable<Taches> taches = tachess;
        for (Taches tacher : taches) {
            String base64Data = Base64.getEncoder().encodeToString(tacher.getImage());
            if (tacher.getImage() != null) {
                tacher.setImgString(base64Data);

            }
        }

        model.addAttribute("taches", taches);

        return mainController.Client(model, session);

    }

    @PostMapping("/validerTache")
    public void validerTache(@RequestParam("idNotif") Long idNotif, @RequestParam("idM") Long idM,@RequestParam("idFils") List<Long> idF) {
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        Taches tache = notification.getTaches();
        Maintenancier maintenancier = maintenancierRepository.findById(idM).orElse(null);
        tache.setMaintenancier(maintenancier);
        // notification.getMaintenanciers().remove(maintenancier);
        notification.setIdMaintenancier(idM);
        tache.setEtat(1);
        maintenancier.setStatus(false);
        tachesRepository.save(tache);
        maintenancierRepository.save(maintenancier);
        notificationRepository.save(notification);
        if(idF != null){
            System.out.println(idF);
        for(Long elt:idF){
            Maintenancier mainFils = maintenancierRepository.findById(elt).orElse(null);
            mainFils.setStatus(false);
            maintenancierRepository.save(mainFils);
        }}
    }

    @PostMapping("/soumettrePrixF")
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

    @PostMapping("/terminerTache")
    public void terminerTache(@RequestParam("idT") Long idT, @RequestParam("idM") Long idM,@RequestParam("prix") Integer prix) {
        // Long id = (Long) session.getAttribute("id");
        Maintenancier maintenancier = maintenancierRepository.findById(idM).orElse(null);
        maintenancier.setStatus(true);
        Taches taches = tachesRepository.findById(idT).orElse(null);
        taches.setEtat(2);
        taches.setCout(prix);
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
