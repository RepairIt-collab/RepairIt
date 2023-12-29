package com.app.FixIt.CONTROLLER.Connexion;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.FixIt.CONTROLLER.Accueil.Accueil;
import com.app.FixIt.CONTROLLER.Maintenance.Maintenance;
import com.app.FixIt.DTO.Maintenance.MaintenancierDTO;
import com.app.FixIt.DTO.Maintenance.ClientDTO;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.SERVICE.Maintenance.ClientService;
import com.app.FixIt.SERVICE.Maintenance.EvaluationService;
import com.app.FixIt.SERVICE.Maintenance.MaintenancierService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/RepairIt/Login")
public class Login {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MaintenancierRepository maintenancierRepository;

    @Autowired
    Accueil accueil;

    @Autowired 
    EvaluationService evaluationService;

    @Autowired 
    Maintenance main;

    @GetMapping("/")
    public String Page() {
        // Logique pour récupérer les données nécessaires et préparer le modèle
        
        // Renvoie le nom de la vue (template) à afficher
        return "HTML/Accueil";
    }

    @PostMapping("/Client")
    public String LogInC(@RequestParam("username") String username,@RequestParam("password") String Password,Model model,HttpSession session){
        Client client = clientRepository.findFirstByUsernameAndPassword(username, Password);
        System.out.print(client);
        System.out.println("=====================================clienlogclient");
        if (client != null){
            session.setAttribute("id", client.getId());
            session.setAttribute("name", username);
            return main.Client(model,session);
        } else {
            return accueil.accueil(model);
        }
        
    }

    @PostMapping("/Maintenancier")
    public String LogInM(@RequestParam("username") String username,@RequestParam("password") String Password,Model model,HttpSession session) throws Exception{
        Maintenancier maintenancier = maintenancierRepository.findFirstByUsernameAndPassword(username, Password);
        System.out.print(maintenancier);
        System.out.println("=====================================maintenancierlogmaintenancier");
        if (maintenancier != null){
            System.out.println(maintenancier.getEmail());
            session.setAttribute("id", maintenancier.getId());
            session.setAttribute("name", username);
            return main.Maintenancier(session,model);
        } else{
            return accueil.accueil(model);
        }
        
    }

}