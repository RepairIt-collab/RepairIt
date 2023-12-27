package com.app.FixIt.CONTROLLER.Accueil;

import java.util.List;
import java.util.Optional;

import org.python.antlr.base.mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.CONTROLLER.Maintenance.Maintenance;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;
import com.app.FixIt.REPOSITORY.User.UserRepository;


@Controller
@RequestMapping("/RepairIt")
public class Accueil {


    @Autowired 
    ClientRepository clientRepository;

    @Autowired 
    TachesRepository tachesRepository;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/Accueil")
    public String accueil(Model model) {
        int client = clientRepository.findAll().size();
        int maintenancier = maintenancierRepository.findAll().size();
        int tache= tachesRepository.findAll().size();
        List<Taches> taches = tachesRepository.findAll();
        int i=0;
        
        for (Taches task : taches) {
            if(task.getEtat()==2)
            {
                i+=1;
            }
        }
       
        String js = (String) model.getAttribute("javascriptCode");
        model.addAttribute("nbclient", client);
        model.addAttribute("nbmain", maintenancier);
        model.addAttribute("nbtache", tache);
        model.addAttribute("nbrepair", i);
        model.addAttribute("javascriptCode", js);
        return "HTML/Accueil"; 
    }

    @GetMapping("/Profil/{userId}")
    public String profile(@PathVariable("userId") String userId, Model model){
        Maintenance maintenance=new Maintenance();
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id).orElse(null);
        String filename = maintenance.nomImage(user.getUsername(), user.getId());
        model.addAttribute("filename", filename);
        model.addAttribute("userId", userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userpassword", user.getPassword());
        model.addAttribute("useremail", user.getEmail());
        model.addAttribute("usertelephone", user.getTelephone());
        
        if (maintenancierRepository.findById(id).isPresent()) {
            Optional<Maintenancier> optM = maintenancierRepository.findById(id);
            Maintenancier main = optM.get();
            model.addAttribute("filename", filename);
            model.addAttribute("userId", userId);
            model.addAttribute("usernom", main.getNom_complet());
            model.addAttribute("username", main.getUsername());
            model.addAttribute("userpassword", main.getPassword());
            model.addAttribute("useremail", main.getEmail());
            model.addAttribute("usertelephone", main.getTelephone());
            model.addAttribute("userspecialite", main.getSpecialite());

            return "HTML/profil2";
        }

        return "HTML/profil";
    }

    

    @GetMapping("/Formation")
    public String Formation()
    {
        return "HTML/Formation";
    }

    @GetMapping("/Apropos")
    public String apropos(){
        return "HTML/Documents/Apropos";
    }

    @GetMapping("/Aide")
    public String aide(){
        return "HTML/Documents/Aide";
    }
    
    @GetMapping("/Contract/Maintenancier")
    public String Con_M()
    {
        return "HTML/Documents/ContractM";
    }

    @GetMapping("/Contract/Client")
    public String Con_C()
    {
        return "HTML/Documents/ContractC";
    }

     
}
