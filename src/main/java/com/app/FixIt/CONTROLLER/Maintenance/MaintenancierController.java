package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.DTO.Maintenance.MaintenancierDTO;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MaintenancierController {
    @Autowired
    MaintenancierRepository maintenancierRepository;

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
}
