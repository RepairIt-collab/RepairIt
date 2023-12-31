package com.app.FixIt.CONTROLLER.RateUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.RateUsRepository;
import com.app.FixIt.REPOSITORY.User.UserRepository;
import com.app.FixIt.ENTITIES.RateUs;

import jakarta.servlet.http.HttpSession;

@Controller
public class RateUsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RateUsRepository rateUsRepository;

    @PostMapping("/RateUs")
    public void rateUs(@RequestParam("avis") float avis, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            User user = userRepository.findById(id).orElse(null);
            RateUs rateUs = new RateUs();
            rateUs.setUser(user);
            rateUs.setAvis(avis);
            rateUsRepository.save(rateUs);
        }
    }
}
