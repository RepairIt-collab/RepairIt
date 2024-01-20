package com.app.FixIt.BOUTIQUE.DAO;

import com.app.FixIt.BOUTIQUE.entities.Domaine;

public interface DomaineDAO {

    public Domaine saveDomaine(Domaine domaine) ;
    public Domaine findByName(String nom) ;
}
