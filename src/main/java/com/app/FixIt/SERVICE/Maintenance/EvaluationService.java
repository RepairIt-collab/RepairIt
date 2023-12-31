package com.app.FixIt.SERVICE.Maintenance;

import java.time.LocalDate;
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
        LocalDate currentDate = LocalDate.now();
        // Evaluation evaluation =
        // evaluationRepository.findFirstByDomainAndDateGreaterThanOrderByDateAsc(maintenancier.getSpecialite(),
        // currentDate);
        Evaluation evaluation = evaluationRepository.findFirstByDomain(maintenancier.getSpecialite());
        System.out.println(evaluation.getDate());
        // List<Maintenancier> maintenanciers = new ArrayList<>();
        // maintenanciers.add(maintenancier);

        if (evaluation.getMaintenanciers() == null) {
            List<Maintenancier> maintenanciers = new ArrayList<>();
            maintenanciers.add(maintenancier);
            evaluation.setMaintenanciers(maintenanciers);
        } else {
            evaluation.getMaintenanciers().add(maintenancier);
        }
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
        LocalDate currentDate = LocalDate.now();
        List<Evaluation> evaluations = evaluationRepository.findByDomain(domain);
        if (evaluations.isEmpty()) {
            Evaluation eval = new Evaluation();
            eval.setDomain(domain);
            eval.setDate(currentDate.plusDays(1));
            addQuestions(eval);
            evaluationRepository.save(eval);

        } else {
            for (Evaluation evaluation : evaluations) {
                int compare = currentDate.compareTo((evaluation.getDate()));
                if (compare > 0) {
                    Evaluation eval = new Evaluation();
                    eval.setDomain(domain);
                    eval.setDate(currentDate.plusDays(1));
                    addQuestions(eval);
                    evaluationRepository.save(eval);
                }
            }
        }
    }

    public void createEvaluationIfDateExpired() throws Exception {
        csvImportRunner.importcsv();
        List<String> domains = addDomain();
        for (String domain : domains) {
            expire(domain);
        }
        System.out.println("///////////////////icic aussi");

    }

}
