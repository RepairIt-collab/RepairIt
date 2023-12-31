package com.app.FixIt.CONTROLLER.Boutique;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.FixIt.entities.Domaine;
import com.app.FixIt.service.Boutique.DomaineService;

import org.springframework.ui.Model;


@Controller
@RequestMapping(path = "/boutique")
public class DomaineController {
    
    DomaineService domaineService ;

    
    public DomaineController() {
    }
    public DomaineController(DomaineService domaineService) {
        this.domaineService = domaineService;
    }

    @GetMapping(value="{nom}")
    public Domaine getMethodName(@PathVariable String nom,Model model) {
        Domaine domaine =  this.domaineService.rechercherDomaine(nom) ;
        model.addAttribute("domaine", domaine) ;
        return domaine ;
    }
    

}
