package com.app.FixIt.CONTROLLER.Profile;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.User.UserRepository;

@RestController
public class photoProfile {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/photoProfile")
    public String saveImage(@RequestParam("image") MultipartFile image,@RequestParam("id") String idS) {
        try {
            Long id = Long.parseLong(idS);
            User user = userRepository.findById(id).orElse(null);
            String username = user.getUsername();
            String fileName = username+""+id+".png";
            Path currentPath = Paths.get("").toAbsolutePath();
            Path imagesPath = Paths.get(currentPath.toString(), "src", "main", "resources", "static", "images", "profile");
            System.out.println(imagesPath);
            File destinationFile = new File(imagesPath.toString(), fileName);
            image.transferTo(destinationFile);
            System.out.println(destinationFile.getAbsolutePath());
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
