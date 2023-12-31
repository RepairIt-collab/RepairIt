package com.app.FixIt.CONTROLLER.Connexion;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/RepairIt/SignUp")
public class SignUp {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MaintenancierRepository maintenancierRepository;

    @Autowired
    Maintenance main;

    @Autowired 
    EvaluationService evaluationService;

    @GetMapping("/")
    public String pageSignUp(){
        return "HTML/Connexion";
    }

    @PostMapping("/newClient")
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO client, HttpSession session){
       
       
        ClientService clientService = new ClientService(clientRepository);
        Client newClient = new Client();
        newClient.setUsername(client.getusername());
        newClient.setEmail(client.getEmail());
        newClient.setPassword(client.getPassword());
        newClient.setTelephone(client.getTelephone());
        clientRepository.save(newClient);
        Client c = clientService.saveClient(newClient);
        session.setAttribute("id", c.getId());
        session.setAttribute("name", c.getUsername());
        


        return ResponseEntity.ok(client);
    }

    @PostMapping("/newMaintenancier")
    public String saveMaintenancier(@RequestBody MaintenancierDTO maintenancier, HttpSession session,Model model) throws Exception{
        MaintenancierService maintenancierService= new MaintenancierService(maintenancierRepository);
        Maintenancier newMaintenancier= new Maintenancier();
        System.out.println(maintenancier.getNom_complet());
        newMaintenancier.setNom_complet(maintenancier.getNom_complet());
        newMaintenancier.setUsername(maintenancier.getNom_utilisateur());
        newMaintenancier.setPassword(maintenancier.getPassword());
        newMaintenancier.setEmail(maintenancier.getMail());
        newMaintenancier.setSexe(maintenancier.getSexe());
        newMaintenancier.setTelephone(maintenancier.getTelephone());
        newMaintenancier.setSpecialite(maintenancier.getSpecialite());
        newMaintenancier.setlatitude(maintenancier.getlatitude());
        newMaintenancier.setLongitude(maintenancier.getLongitude());
        newMaintenancier.setStatus(true);
        newMaintenancier.setTest(0);
        
        Maintenancier m=maintenancierService.saveMaintenancier(newMaintenancier);
        evaluationService.createEvaluationIfDateExpired();
        evaluationService.add(newMaintenancier);
        session.setAttribute("id", m.getId());
        session.setAttribute("name", m.getUsername());
        return main.Maintenancier(session,model);
    }

    @GetMapping("/loadClient")
    public ResponseEntity<List<Client>>loadclient()
    {
        List<Client> client=null;
        client=clientRepository.findAll();

        return ResponseEntity.ok(client);
    }

    @GetMapping("/loadMaintenancier")
    public ResponseEntity<List<Maintenancier>>loadMaintenancier()
    {
        List<Maintenancier> main=null;
        main=maintenancierRepository.findAll();

        return ResponseEntity.ok(main);
    }
}