package com.app.FixIt.CONTROLLER.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.FixIt.DTO.Mail.EmailDTO;
import com.app.FixIt.SERVICE.Mail.EmailService;

@Controller
public class EmailController {
    
        private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendemail")
     @ResponseBody
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO.getTo(), emailDTO.getSubject(), emailDTO.getText());
    }
}
