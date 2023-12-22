package com.app.FixIt.SERVICE.Maintenance;

// public class EvaluationScheduler {
    
// }
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EvaluationScheduler {

    @Autowired
    private EvaluationService evaluationService;

    @Scheduled(cron = "0 0 0 * * *") // Exécution quotidienne à minuit
    public void createEvaluationIfDateExpired() {
        List<String> domains = evaluationService.addDomain();
        for(String domain : domains){
            evaluationService.expire(domain);
        }
        
    }
}