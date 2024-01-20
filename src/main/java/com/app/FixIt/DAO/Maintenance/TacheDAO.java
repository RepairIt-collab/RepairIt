package com.app.FixIt.DAO.Maintenance;

import com.app.FixIt.ENTITIES.Maintenance.Taches;

public interface TacheDAO { 
    public Taches saveTache(Taches tache);
    public String DeleteTache(Long Id);
}