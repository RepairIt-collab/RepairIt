package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Questions;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.Type;
import com.app.FixIt.REPOSITORY.Maintenance.EvaluationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.NotificationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;
import com.app.FixIt.SERVICE.Maintenance.EvaluationService;

@RestController
public class EvaluationController {
    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    TachesRepository tachesRepository ;

    @PostMapping("/validerEval/{idM}")
    public Maintenancier validerEval(@PathVariable("idM") Long id, @RequestBody List<Questions> question) throws Exception{
        int notefinal = 0;
        // LocalDateTime localDate = LocalDateTime.now();
        Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
        // Evaluation evaluation = evaluationRepository.findByMaintenanciersAndDateGreaterThanOrderByDateAsc(maintenancier,localDate);
        Evaluation evaluation =evaluationService.evaluationLast(maintenancier);
        List<Questions> questions = evaluation.getQuestions();
        for (Questions question1 : questions) {
            for (Questions question2 : question) {
                if (question1.getId().equals(question2.getId()) && question1.getCorrect() == question2.getCorrect()) {
                    notefinal += 1;
                }
            }
        }
        if(notefinal == 20 || notefinal == 19 || notefinal == 18){
            maintenancier.setTest(2);
            maintenancier.setNotes(notefinal);
            List<Taches> taches = tachesRepository.findByEtatAndType(0, Type.valueOf(maintenancier.getSpecialite()));
            for(Taches tache : taches){
            Notification notification = notificationRepository.findByTaches(tache);
            if(notification.getMaintenanciers() != null){
                notification.getMaintenanciers().add(maintenancier);
            } else{
                List<Maintenancier> maintenanciers = new ArrayList<>();
                maintenanciers.add(maintenancier);
                notification.setMaintenanciers(maintenanciers);
            }
            notificationRepository.save(notification);
        }
            maintenancierRepository.save(maintenancier);

        }
        if(notefinal<12){
            evaluationService.createEvaluationIfDateExpired(maintenancier.getSpecialite());
        evaluationService.add(maintenancier);
        }
        if (notefinal >= 12 && notefinal <= 17) {
            maintenancier.setTest(1);
            maintenancier.setNotes(notefinal);
            maintenancierRepository.save(maintenancier);
        }
        return maintenancier;
    }

    @PostMapping("/findParrain")
    public Notification findMaintenancierParain(@RequestParam("id") Long id) {
        Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
        Notification notification = new Notification();
        // List<Maintenancier> maintenanciers = maintenancierRepository.findByFilleuls(maintenancier.getId());
        List<Maintenancier> maintenanciers = maintenancierRepository.findByTestAndSpecialite(2,
                    maintenancier.getSpecialite());
        notification.setMaintenanciers(maintenanciers);
        String messageString = "Nouveau parrainage concernant le maintenancier " + maintenancier.getNom_complet()
                + " de  la specialite " + maintenancier.getSpecialite()+"///" +maintenancier.getId();
        notification.setMessage(messageString);
        notificationRepository.save(notification);
        return notification;

    }

    @PostMapping("/validerParrainage")
    public Notification validerParrainage(@RequestParam("idM") Long idM, @RequestParam("idF") Long idF) {
        Maintenancier maintenancierf = maintenancierRepository.findById(idF).orElse(null);
        Maintenancier maintenancierP = maintenancierRepository.findById(idM).orElse(null);
        if (maintenancierP.getIdfilleuls() == null) {
            List<Long> liste = new ArrayList<>();
            liste.add(maintenancierf.getId());
            maintenancierP.setIdfilleuls(liste);
        } else {
            maintenancierP.getIdfilleuls().add(maintenancierf.getId());
        }
        maintenancierRepository.save(maintenancierP);
        Notification notification = new Notification();
        String meString = "Vous avez etes Parrainer par "+maintenancierP.getUsername()+" decouvrez plus d'info dans la rubrique 'Mon parrain'";
        notification.setMessage(meString);
        List<Maintenancier> main = new ArrayList<>();
        main.add(maintenancierf);
        notification.setMaintenanciers(main);
        notificationRepository.save(notification);
        return notification;

    }

    @PostMapping("/validerFilleul")
    public Notification validerFilleul(@RequestParam("idM") Long idM, @RequestParam("idF") Long idF) {
        Maintenancier maintenancierf = maintenancierRepository.findById(idF).orElse(null);
        Maintenancier maintenancierP = maintenancierRepository.findById(idM).orElse(null);
        maintenancierf.setTest(2);
        maintenancierP.getIdfilleuls().remove(idF);
        maintenancierRepository.save(maintenancierP);
        maintenancierRepository.save(maintenancierf);

        Notification notification = new Notification();
        String meString = "Vous avez etes Valider par votre parrain "+maintenancierP.getUsername()+" vous pouvez desormais effectuer vos taches seul et recevoir votre paie ";
        notification.setMessage(meString);
        List<Maintenancier> main = new ArrayList<>();
        main.add(maintenancierf);
        notification.setMaintenanciers(main);
        notificationRepository.save(notification);
        return notification;

    }


}
