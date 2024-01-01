package com.app.FixIt.BOUTIQUE.services;

import org.springframework.stereotype.Service;

import com.app.FixIt.BOUTIQUE.DAO.DomaineDAO;
import com.app.FixIt.BOUTIQUE.entities.Domaine;
import com.app.FixIt.BOUTIQUE.repository.DomaineRepository;


@Service
public class DomaineServices implements DomaineDAO {

    public DomaineRepository DomaineRepository ;
    public DomaineServices(DomaineRepository DomaineRepository){
        this.DomaineRepository = DomaineRepository ;
    }

    @Override
    public Domaine saveDomaine(Domaine domaine){
        domaine = this.DomaineRepository.save(domaine) ;
        return domaine ;
    }
    @Override
    public Domaine findByName(String nom) {
        Domaine domaine = DomaineRepository.findByDomaine(nom) ;
        if(domaine == null){
            domaine = new Domaine(nom);
            domaine = DomaineRepository.save(domaine);
        }
        return domaine;
    }
    
}
