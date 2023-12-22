package com.app.FixIt.SERVICE.Maintenance;

import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.*;
import com.app.FixIt.DAO.Maintenance.EquipementsDAO;
import com.app.FixIt.ENTITIES.*;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EquipementsService implements EquipementsDAO{

    private EquipementsRepository equipementsRepository;
    public EquipementsService(EquipementsRepository equipementsRepository){
        this.equipementsRepository=equipementsRepository;
    }

    @Override
    public Equipements saveEquipements(Equipements equipements){
        equipements=equipementsRepository.save(equipements);
        return equipements;
    }

    @Override
    public String DeleteEquipements(Long id){
        equipementsRepository.deleteById(id);
        return "Suppression reussie";
    }
}
