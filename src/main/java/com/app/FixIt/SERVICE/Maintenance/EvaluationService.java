package com.app.FixIt.SERVICE.Maintenance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.FixIt.ENTITIES.Maintenance.Evaluation;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Questions;
import com.app.FixIt.REPOSITORY.Maintenance.EvaluationRepository;
import com.app.FixIt.REPOSITORY.Maintenance.QuestionsRepository;

import java.util.Collections;

@Service
public class EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    CsvImportRunner csvImportRunner;

    public void eval() {

    }

    public void add(Maintenancier maintenancier) {
        // LocalDateTime currentDate = LocalDateTime.now();
        expire(maintenancier.getSpecialite());
        // Evaluation evaluation = evaluationRepository.findFirstByDomainAndDateGreaterThanOrderByDateAsc(maintenancier.getSpecialite(),currentDate);
        Evaluation evaluation = findByDomainAndEvalDate(maintenancier.getSpecialite());
        
        System.out.println(evaluation.getDate());
        if (evaluation.getMaintenanciers() == null) {
            List<Maintenancier> maintenanciers = new ArrayList<>();
            maintenanciers.add(maintenancier);
            evaluation.setMaintenanciers(maintenanciers);
        } else {
            Boolean isPresent = false;
            List<Maintenancier> mainList = evaluation.getMaintenanciers();
            for(Maintenancier main : mainList){
                if(maintenancier == main){
                    isPresent = true;//le maintenancier apartient deja a une evaluation en cours
                }
            }
            if(isPresent == false){
            evaluation.getMaintenanciers().add(maintenancier);
        }}
        evaluationRepository.save(evaluation);
    }

    public void addQuestions(Evaluation evaluation) {
        List<Questions> questions = selectRandomQuestions(20, evaluation.getDomain());
        evaluation.setQuestions(questions);
        evaluationRepository.save(evaluation);
    }

    private List<Questions> selectRandomQuestions(int numQuestions, String domain) {
        // Récupération de toutes les questions du domaine spécifié
        List<Questions> allQuestions = questionsRepository.findByDomain(domain);

        // Vérification du nombre total de questions disponibles
        int totalQuestions = allQuestions.size();
        if (totalQuestions < numQuestions) {
            throw new IllegalArgumentException(
                    "Il n'y a pas suffisamment de questions disponibles dans le domaine spécifié.");
        }

        // Mélange aléatoire de la liste de questions
        Collections.shuffle(allQuestions);

        // Sélection des premières numQuestions questions
        List<Questions> selectedQuestions = new ArrayList<>(allQuestions.subList(0, numQuestions));

        return selectedQuestions;
    }

    public List<String> addDomain() {
        List<String> domain = new ArrayList<>();
        domain.add("Electricite");
        domain.add("Electromenage");
        domain.add("Mecanique");
        domain.add("Informatique-Telephone");
        domain.add("Informatique-Ordinateur");
        domain.add("Plomberie");
        domain.add("Menuiserie");
        // domain.add("Froid et Climatisation");
        domain.add("Medicale");
        domain.add("Industrielle");
        return domain;
    }

    public void expire(String domain) {
        LocalDateTime currentDatetime = LocalDateTime.now();
        List<Evaluation> evaluations = evaluationRepository.findByDomain(domain);
        System.out.println(evaluations);
        if (evaluations.isEmpty()) {
            System.out.println("evaluation empty");
            Evaluation evalu = new Evaluation();
            evalu.setDomain(domain);
            evalu.setDate(currentDatetime.plusMinutes(3));
            evaluationRepository.save(evalu);
            addQuestions(evalu);

        } else {
            System.out.println("evaluation is not empty");
            for (Evaluation evaluation : evaluations) {
                System.out.println(evaluation.getDomain()+"//////////"+evaluation.getDate());
                int compare = currentDatetime.compareTo((evaluation.getDate()));
                if (compare > 0 || compare == 0) {
                    System.out.println(compare);
                    System.out.println(evaluation.getDomain()+"\\\\\\\\\\\\\\\\\\"+evaluation.getDate());
                    Evaluation eval = new Evaluation();
                    eval.setDomain(domain);
                    eval.setDate(currentDatetime.plusMinutes(3));
                    evaluationRepository.save(eval);
                    addQuestions(eval);
                }
            }
        }
    }

    public void createEvaluationIfDateExpired(String domain) throws Exception {
        csvImportRunner.importcsv();
            expire(domain);
        System.out.println("///////////////////icic aussi");

    }


    public Evaluation findByDomainAndEvalDate(String specialite){
        LocalDateTime closestDateTime = null;
        Duration closestDuration = null;
        LocalDateTime now = LocalDateTime.now();
        Evaluation evalfinal = new Evaluation();
        List<Evaluation> evaluations = evaluationRepository.findByDomain(specialite);
        for(Evaluation evaluation:evaluations){
            LocalDateTime dateTime = evaluation.getDate();
            if (dateTime.isAfter(now)) { // Vérifie si la date est dans le futur
                Duration duration = Duration.between(now, dateTime);
                if (closestDateTime == null || duration.compareTo(closestDuration) < 0) {
                    closestDateTime = dateTime;
                    closestDuration = duration;
                    evalfinal = evaluation;
                }
            }
        }
        return evalfinal;

    }

    public Evaluation evaluationLast(Maintenancier maintenancier){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nearestFutureDateTime = null;
        LocalDateTime nearestPastDateTime = null;
        Evaluation evalfinalFutur = new Evaluation();
        Evaluation evalfinalPasse = new Evaluation();
        List<Evaluation> evaluations = evaluationRepository.findByMaintenanciers(maintenancier);
        System.out.println(evaluations);
        for(Evaluation evaluation:evaluations){
            LocalDateTime dateTime = evaluation.getDate();
            System.out.println(dateTime);
             // Vérifie si la date est dans le futur
                if (dateTime.isAfter(currentDateTime)) {
                    System.out.println("futur");
                if (nearestFutureDateTime == null || dateTime.isBefore(nearestFutureDateTime)) {
                    System.out.println("futur    futur");
                    nearestFutureDateTime = dateTime;
                    evalfinalFutur = evaluation;
                }
            } else {
                System.out.println("passee");
                if (nearestPastDateTime == null || dateTime.isAfter(nearestPastDateTime)) {
                    System.out.println("passee        passee");
                    nearestPastDateTime = dateTime;
                    evalfinalPasse = evaluation;
                }
            }
            }
        
        System.out.println(nearestFutureDateTime);
        System.out.println(nearestPastDateTime);
        if (nearestFutureDateTime != null) {
            return evalfinalFutur;
        } else {
            return evalfinalPasse;
        }

    }

}
