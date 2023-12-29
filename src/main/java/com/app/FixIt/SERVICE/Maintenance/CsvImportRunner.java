package com.app.FixIt.SERVICE.Maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.app.FixIt.ENTITIES.Maintenance.Questions;
import com.app.FixIt.REPOSITORY.Maintenance.QuestionsRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

 @Component
    public class CsvImportRunner {

        @Autowired
        private QuestionsRepository questionRepository;

        // @Override
        public  void importcsv() throws Exception {
            List<Questions> questions = questionRepository.findAll(); 
            if(questions.size() == 0){
            File csvFile = ResourceUtils.getFile("src/main/resources/static/Evaluation/questions.csv");
            BufferedReader br = new BufferedReader(new FileReader(csvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Long id = Long.parseLong(data[0]);
                String question = data[1];
                String option1 = data[2];
                String option2 = data[3];
                String option3 = data[4];
                Integer answer = Integer.parseInt(data[5]);
                String domain = data[6];

                Questions q = new Questions(question, option1, option2, option3, answer, domain);
                questionRepository.save(q);
            }

            br.close();
        }}
    }
