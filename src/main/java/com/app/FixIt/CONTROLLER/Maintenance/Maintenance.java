package com.app.FixIt.CONTROLLER.Maintenance;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpSession;

import com.app.FixIt.CONTROLLER.Accueil.Accueil;
import com.app.FixIt.ENTITIES.*;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.Type;
import com.app.FixIt.REPOSITORY.*;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EvaluationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.NotificationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.QuestionsRepository;
import com.app.FixIt.SERVICE.Maintenance.ClientService;

@Controller
@RequestMapping("/RepairIt")
public class Maintenance {

    @Autowired 
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

     @Autowired
     Accueil accueil;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EvaluationRepository  evaluationRepository;

    @Autowired
    QuestionsRepository questionsRepository;


    @GetMapping("/Client")
    public String Client(Model model,HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if(id != null){
        Client client = clientRepository.findById(id).orElse(null);
        List<Taches> tache = (List<Taches>) model.getAttribute("taches");
        Iterable<Taches> taches = tache;
       
        
        model.addAttribute("taches", taches);
        model.addAttribute("type", Type.values());
        model.addAttribute("client", client);
        return "HTML/Client";
        } 
        else {
        String javascriptCode = "openPopup1();";
        model.addAttribute("javascriptCode", javascriptCode);
        return accueil.accueil(model);
        }

         
    }

    @GetMapping("/Maintenancier")
    public String Maintenancier(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("id");
        if(id != null){
            
        Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
            List<Notification> notifications = notificationRepository.findByMaintenanciers(maintenancier);
            Iterable<Notification> notificationsI=notifications;
       //     Evaluation evaluation = evaluationRepository.findByMaintenanciers(maintenancier);
      //      model.addAttribute("evaluation", evaluation);
            List<Notification> notification2 = notificationRepository.findByIdMaintenancier(id);
            Iterable<Notification> noIterable = notification2;
            model.addAttribute("notifM", noIterable);
            System.out.println(notification2);
            model.addAttribute("notifications", notificationsI);
            model.addAttribute("maintenancier", maintenancier);
        return "HTML/Maintenancier";
             } else {
        String javascriptCode = "openPopup2();";
        model.addAttribute("javascriptCode", javascriptCode);
        return accueil.accueil(model);
    }


    }

   
}