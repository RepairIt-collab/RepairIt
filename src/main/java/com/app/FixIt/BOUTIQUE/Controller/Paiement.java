package com.app.FixIt.BOUTIQUE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.FixIt.BOUTIQUE.entities.Panier;
import com.app.FixIt.BOUTIQUE.entities.Produit;
import com.app.FixIt.BOUTIQUE.repository.PanierRepository;
import com.app.FixIt.BOUTIQUE.services.DomaineServices;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/RepairIt")
public class Paiement {
    
    @Autowired 
    private PanierRepository panierRepository;
    @Autowired
    DomaineServices domaineServices ;


    @GetMapping("/Boutique/Paiement")
    public String Paiements(Model model,HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if(id!=null)
        {
            Panier panier = panierRepository.findByUserId(id);
            if(panier!=null)
            {
                List<Produit> existingProduits = panier.getProduit();
                 if(existingProduits!=null)
               {
                model.addAttribute("panier", existingProduits);
               } 
            }  
        }

        return "HTML/Paiement";
    }
}
