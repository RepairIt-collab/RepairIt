package com.app.FixIt.CONTROLLER.Accueil;

import org.python.antlr.base.mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.User.UserRepository;
import com.app.FixIt.CONTROLLER.Maintenance.Maintenance;

@Controller
@RequestMapping("/RepairIt")
public class Accueil {


    @Autowired 
    ClientRepository clientRepository;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/Accueil")
    public String accueil(Model model) {
      //  int client = clientRepository.findAll().size();
     //   int maintenancier = maintenancierRepository.findAll().size();
        String js = (String) model.getAttribute("javascriptCode");
      //  model.addAttribute("nbclient", client);
      //  model.addAttribute("nbmain", maintenancier);
        model.addAttribute("javascriptCode", js);
        return "HTML/Accueil"; 
    }

    @GetMapping("/Formation")
    public String Formation()
    {
        return "HTML/Formation";
    }

    @GetMapping("/Profil/{userId}")
    public String profile(@PathVariable("userId") String userId, Model model){
        Maintenance maintenance=new Maintenance();
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id).orElse(null);
        String filename = maintenance.nomImage(user.getUsername(), user.getId());
        model.addAttribute("filename", filename);
        model.addAttribute("userId", userId);
        return "HTML/profil";
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
