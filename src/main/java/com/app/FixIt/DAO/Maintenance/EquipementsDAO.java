package com.app.FixIt.DAO.Maintenance;

import com.app.FixIt.ENTITIES.Maintenance.Equipements;

public interface EquipementsDAO {
    public Equipements saveEquipements(Equipements equipements);
    public String DeleteEquipements(Long id);
}
