package com.app.FixIt.CONTROLLER.Maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.FixIt.DTO.Maintenance.MaintenancierDTO;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;

public class MaintenancierController {
    @Autowired
    MaintenancierRepository maintenancierRepository;

    
    @PostMapping("/update-location")
    public void update_location(@RequestBody MaintenancierDTO user){
        String username = user.getNom_utilisateur();
        Maintenancier maintenancier = maintenancierRepository.findFirstByUsername(username);
        maintenancier.setlatitude(user.getlatitude());
        maintenancier.setLongitude(user.getLongitude());
        maintenancierRepository.save(maintenancier);
    }

    @PostMapping("/formation")
    public void formation(){
        
    }
}
