package com.app.FixIt.BOUTIQUE.Controller;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/RepairIt")
public class Administrator {


       @PostMapping("/Boutique/Administrator/{password}")   
     public ResponseEntity<String> Identification(@PathVariable String password)
    {
        password = password.trim();
        System.out.print(password);
        if (password.equals("FAKENDETKE_2024")) {
            return ResponseEntity.ok("Valide");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }
    }
    
}
