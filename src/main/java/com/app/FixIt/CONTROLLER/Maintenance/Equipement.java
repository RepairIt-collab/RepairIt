package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.DTO.Maintenance.EquipementDTO;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.SERVICE.Maintenance.EquipementsService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/RepairIt/Client/Equipement")
public class Equipement {
    @Autowired
    private EquipementsRepository equipementsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/CreerEquipement")
    public ResponseEntity<EquipementDTO> creerEquipements(HttpSession session,
            @RequestBody EquipementDTO equipementDTO) {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            Client client = clientRepository.findById(id).orElse(null);
            EquipementsService equipementsService = new EquipementsService(equipementsRepository);
            Equipements equipement = new Equipements();

            equipement.setNom(equipementDTO.getNom());
            equipement.setEtats(2);
            equipement.setPhoto(equipementDTO.getPhoto());
            equipement.setType(equipementDTO.getType());
            equipement.setClient(client);

            equipement = equipementsService.saveEquipements(equipement);
            equipementDTO.setId(equipement.getId());
        }

        return ResponseEntity.ok(equipementDTO);
    }

    @DeleteMapping("/SuprimerEquipements/{id}")
    public String DeleteEquipements(@PathVariable Long id) {
        EquipementsService equipementsService = new EquipementsService(equipementsRepository);
        equipementsService.DeleteEquipements(id);

        return "Suppression réalisée";
    }

    @GetMapping("/loadEquipement")
    public List<Equipements> loadEquipement(HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        // if(id != null){
        Client client = clientRepository.findById(id).orElse(null);
        List<Equipements> equipements = equipementsRepository.findByClient(client);
        return equipements;
        // }
    }


    public ResponseEntity<EquipementDTO> updataEquipement(Long id,@RequestBody EquipementDTO equipementDTO) {

            // EquipementsService equipementsService = new EquipementsService(equipementsRepository);
            Equipements equipement = equipementsRepository.findById(id).orElse(null);

            equipement.setNom(equipementDTO.getNom());
            equipement.setEtats(2);
            equipement.setPhoto(equipementDTO.getPhoto());
            equipement.setType(equipementDTO.getType());
            equipementsRepository.save(equipement);
        

        return ResponseEntity.ok(equipementDTO);
    
    }


}