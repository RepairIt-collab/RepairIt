package com.app.FixIt.CONTROLLER.Cnn;

import java.io.File;
import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Predict {


    private String saveImage(MultipartFile image) {
        try {
            //String fileName = image.getOriginalFilename();
            File destinationFile = new File("/home/orlane/RepairIt/Images/" + "im.png");
            image.transferTo(destinationFile);
            return destinationFile.getAbsolutePath();
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/flask")
    public String predictFlask(@RequestParam("image") MultipartFile image) {
        RestTemplate restTemplate;
        restTemplate = new RestTemplate();
        try {
            String chemin = saveImage(image);
            HttpEntity<String> entity = new HttpEntity<String>(chemin);
            ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:5000/predict", entity, String.class);
            String r = response.getBody().toString();
            return r;
        } catch (Exception e) {
            return "Erreur lors du traitement de l'image : " + e.getMessage();
        }
    }
}