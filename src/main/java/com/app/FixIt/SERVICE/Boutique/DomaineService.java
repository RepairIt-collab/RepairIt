package com.app.FixIt.SERVICE.Boutique;

import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Boutique.DomaineDAO;
import com.app.FixIt.ENTITIES.Boutique.Domaine;
import com.app.FixIt.REPOSITORY.Boutique.DomaineRespository;



@Service
public class DomaineService implements DomaineDAO{

    DomaineRespository domaineRespository ;

    public DomaineService(DomaineRespository domaineRespository) {
        this.domaineRespository = domaineRespository;
    }

    public void CreerDomaine(Domaine domaine){

        this.domaineRespository.save(domaine) ;
    }
    
    @Override
    public Domaine rechercherDomaine(String nom){

        return this.domaineRespository.findByNom(nom);
    }
}
