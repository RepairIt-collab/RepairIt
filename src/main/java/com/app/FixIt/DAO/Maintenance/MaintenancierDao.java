package com.app.FixIt.DAO.Maintenance;


import java.util.List;

import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;

public interface MaintenancierDao {
    public Maintenancier saveMaintenancier(Maintenancier maintenancier);  
    public Maintenancier findById(Long id);
    public List<Maintenancier> findMaintenancier(Taches taches); 
}
