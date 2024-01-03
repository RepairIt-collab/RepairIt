package com.app.FixIt.CONTROLLER.Maintenance;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.app.FixIt.CONTROLLER.Accueil.Accueil;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Equipements;
import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Notification;
import com.app.FixIt.ENTITIES.Maintenance.Questions;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.Type;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EquipementsRepository;
import com.app.FixIt.REPOSITORY.Maintenance.EvaluationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.NotificationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.QuestionsRepository;
import com.app.FixIt.REPOSITORY.User.UserRepository;
import com.app.FixIt.SERVICE.Maintenance.ClientService;
import com.app.FixIt.SERVICE.Maintenance.EquipementsService;
import com.app.FixIt.SERVICE.Maintenance.EvaluationService;

@Controller
@RequestMapping("/RepairIt")
public class Maintenance {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Accueil accueil;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    EquipementsRepository equipementsRepository;

    @GetMapping("/Client")
    public String Client(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            Client client = clientRepository.findById(id).orElse(null);
            List<Equipements> equipements = equipementsRepository.findByClient(client);
            // Iterable<Equipements> equIterable = equipements;
            List<Taches> tache = (List<Taches>) model.getAttribute("taches");
            Iterable<Taches> taches = tache;
            String filename = nomImage(client.getUsername(), client.getId());
            model.addAttribute("taches", taches);
            model.addAttribute("equipements", equipements);
            model.addAttribute("filename", filename);
            model.addAttribute("type", Type.values());
            model.addAttribute("client", client);
            return "HTML/Client";
        } else {
            String javascriptCode = "openPopup1();";
            model.addAttribute("javascriptCode", javascriptCode);
            return accueil.accueil(model);
        }

    }

    @GetMapping("/Maintenancier")
    public String Maintenancier(HttpSession session, Model model) throws Exception {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {

            Maintenancier maintenancier = maintenancierRepository.findById(id).orElse(null);
            List<Notification> notifications = notificationRepository.findByMaintenanciers(maintenancier);
            Iterable<Notification> notificationsI = notifications;
            // evaluationService.createEvaluationIfDateExpired();
            if (maintenancier.getTest() == 0) {
                System.out.println("evaluation.getDomain()+--------+q.getQuestion()");
                Evaluation evaluation = evaluationRepository.findByMaintenanciers(maintenancier);
                model.addAttribute("evaluation", evaluation);
                for (Questions q : evaluation.getQuestions()) {
                    System.out.println(evaluation.getDomain() + "--------" + q.getQuestion());
                }
            }
            List<Notification> notification2 = notificationRepository.findByIdMaintenancier(id);
            Iterable<Notification> noIterable = notification2;
            model.addAttribute("notifM", noIterable);
            System.out.println(notification2);
            List<Long> listfilleul = maintenancier.getIdfilleuls();
            List<Maintenancier> filleuList = new ArrayList<>();
            if (listfilleul != null) {
                for (Long idF : listfilleul) {
                    Maintenancier main = maintenancierRepository.findById(idF).orElse(null);
                    filleuList.add(main);
                }
                Iterable<Maintenancier> filleul = filleuList;
                model.addAttribute("filleuls", filleul);
            }
            if (maintenancier.getTest() == 1) {
                List<Maintenancier> mains = maintenancierRepository.findAll();
                // Maintenancier monParrain;
                for(Maintenancier monParrain:mains){
                    List<Long> Llong= monParrain.getIdfilleuls();
                    if(Llong != null){
                    if(Llong.contains(id)){
                        List<Notification> notifParrain = notificationRepository.findByMaintenanciers(monParrain);
                        Iterable<Notification> notifParrainI = notifParrain;
                        model.addAttribute("notifParrain", notifParrainI);
                        List<Notification> notification2P = notificationRepository.findByIdMaintenancier(monParrain.getId());
                        if(maintenancier.getStatus() == false){
                        Iterable<Notification> noIterableP = notification2P;
                        model.addAttribute("notifMP", noIterableP);}
                        model.addAttribute("monParrain", monParrain);
                        String parrain = "estNotNul";
                model.addAttribute("parrain", parrain);
                    }}
                }

                // Maintenancier monParrain = maintenancierRepository.findByFilleulId(id);
                

            }
            String filename = nomImage(maintenancier.getUsername(), maintenancier.getId());
            model.addAttribute("notifications", notificationsI);
            model.addAttribute("maintenancier", maintenancier);

            model.addAttribute("filename", filename);
            return "HTML/Maintenancier";
        } else {
            String javascriptCode = "openPopup2();";
            model.addAttribute("javascriptCode", javascriptCode);
            return accueil.accueil(model);
        }

    }

    // chercher si il existe une image constituer du Username+id.type
    // si oui on envoie le nom /images/profile/Username+id.type
    // si non on envoie /images/COMPTE 1.png
    public String nomImage(String username, Long id) {
        Path currentPath = Paths.get("").toAbsolutePath();
        Path imagesPath = Paths.get(currentPath.toString(), "src", "main", "resources", "static", "images", "profile");
        String destinationPath = imagesPath.toString();

        File destinationFolder = new File(destinationPath);
        String filename = username + "" + id + ".png";
        String NameReturn = "/images/COMPTE 1.png";

        if (destinationFolder.exists() && destinationFolder.isDirectory()) {
            // Afficher tous les fichiers dans la destination
            File[] files = destinationFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                    System.out.println("========" + filename);
                    if (filename.equals(file.getName())) {
                        NameReturn = "/images/profile/" + filename;
                    }

                }
            }

            System.out.println("--------" + NameReturn);
        }
        return NameReturn;
    }

    @PostMapping("/EnvoieMail/{mail}/{Mpasse}")
    public ResponseEntity<String> modifMail(@PathVariable String mail, @PathVariable String Mpasse) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getEmail().equals(mail)) {
                user.setEmail(mail);
                user.setPassword(Mpasse);
                userRepository.save(user); // Mise à jour de l'utilisateur dans la base de données
                break; // Sortie de la boucle après la mise à jour du premier utilisateur correspondant
            }
        }
        return ResponseEntity.ok("E-mail envoyé avec succès");
    }

}