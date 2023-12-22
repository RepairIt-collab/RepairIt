package com.app.FixIt.CONTROLLER.Maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.DTO.Maintenance.EquipementDTO;
import com.app.FixIt.ENTITIES.*;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.SERVICE.Maintenance.EquipementsService;

@RestController
@RequestMapping("/RepairIt/Client/Equipement")
public class Equipement {
    @Autowired
    private EquipementsRepository equipementsRepository;
 
    @PostMapping("/CreerEquipement")
    public ResponseEntity<EquipementDTO> creerEquipements(@RequestBody EquipementDTO equipementDTO){
        EquipementsService equipementsService = new EquipementsService(equipementsRepository);
        Equipements equipement = new Equipements();

        equipement.setNom(equipementDTO.getNom());
        equipement.setEtats(0);
        equipement.setPhoto(equipementDTO.getPhoto());
        equipement.setType(equipementDTO.getType());

        equipement=equipementsService.saveEquipements(equipement);
        equipementDTO.setId(equipement.getId());

        return ResponseEntity.ok(equipementDTO);
    }

     @DeleteMapping("/SuprimerEquipements/{id}")
     public String DeleteEquipements(@PathVariable Long id) {
        EquipementsService equipementsService = new EquipementsService(equipementsRepository);
        equipementsService.DeleteEquipements(id);

        return "Suppression réalisée";
    }

}
