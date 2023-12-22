package com.app.FixIt.CONTROLLER.Maintenance;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Questions;
import com.app.FixIt.REPOSITORY.Maintenance.EvaluationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.NotificationRepository;

// @Controller
@RestController
public class EvaluationController {
    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EvaluationRepository evaluationRepository;
    @PostMapping("/validerEval/{idM}")
   public Maintenancier validerEval(@PathVariable("idM") Long id,@RequestBody List<Questions> question){
    int notefinal = 0;
    Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
    Evaluation evaluation = evaluationRepository.findByMaintenanciers(maintenancier);
    List<Questions> questions = evaluation.getQuestions() ;
    for(Questions question1:questions){
        for(Questions question2:question){
            if (question1.getId().equals(question2.getId()) && question1.getCorrect() == question2.getCorrect()) {
                notefinal += 1;
            }
        }
    }
    if (notefinal>=12){
        maintenancier.setTest(1);
        maintenancier.setNotes(notefinal);
        maintenancierRepository.save(maintenancier);
        List<Maintenancier> maintenanciersParain = maintenancierRepository.findByTestAndSpecialite(2, maintenancier.getSpecialite());
        for(Maintenancier elt : maintenanciersParain){
            if(elt.getIdfilleuls() == null){
                List<Long> liste = new ArrayList<>();
                liste.add(maintenancier.getId());
                elt.setIdfilleuls(liste);
            } else{
                elt.getIdfilleuls().add(maintenancier.getId());
            }
            maintenancierRepository.save(elt);
        }

    }
    return maintenancier;
   }

   @GetMapping("/findParrain")
   public Notification findMaintenancierParain(@RequestParam("id") Long id ){
    Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
    Notification notification = new Notification();
    List<Maintenancier> maintenanciers = maintenancierRepository.findByFilleuls(maintenancier.getId());
    notification.setMaintenanciers(maintenanciers);
    String messageString  = "Nouveau parrainage concernant le maintenancier "+maintenancier.getNom_complet()+" de  la specialite "+maintenancier.getSpecialite();//"Taches concernant un "+equipements.getNom() +" de type "+ tache.getType()+" Description du Probleme " + tache.getDescription()+" a realiser au plus tard " + tache.getDate() + "//"+tache.getId();
    notification.setMessage(messageString);
    notificationRepository.save(notification);
    return notification;

   }

   @GetMapping("/validerParrainage")
   public void validerParrainage(@RequestParam("idM") Long idM , @RequestParam("idF") Long idF){
    Maintenancier maintenancierf = maintenancierRepository.findById(idF).orElse(null);
    List<Maintenancier> maintenanciers = maintenancierRepository.findByFilleuls(maintenancierf.getId());
    for( Maintenancier elt :maintenanciers){
        elt.getIdfilleuls().remove(idF);
        maintenancierRepository.save(elt);
    }
    Maintenancier maintenancierP = maintenancierRepository.findById(idM).orElse(null);
            if(maintenancierP.getIdfilleuls() == null){
                List<Long> liste = new ArrayList<>();
                liste.add(maintenancierf.getId());
                maintenancierP.setIdfilleuls(liste);
            } else{
                maintenancierP.getIdfilleuls().add(maintenancierf.getId());
            }
            maintenancierRepository.save(maintenancierP);


   }

   @GetMapping("/validerFilleul")
   public void validerFilleul(@RequestParam("idM") Long idM , @RequestParam("idF") Long idF){
    Maintenancier maintenancierf = maintenancierRepository.findById(idF).orElse(null);
    Maintenancier maintenancierP = maintenancierRepository.findById(idM).orElse(null);
    maintenancierf.setTest(2);
    maintenancierP.getIdfilleuls().remove(idF);
    maintenancierRepository.save(maintenancierP);
    maintenancierRepository.save(maintenancierf);


   }
}
