package com.app.FixIt.Websocket;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    ClientRepository clientRepository;


    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/notification/{userId}")
    public void send(@DestinationVariable Long userId,String message){

        System.out.println("Méthode du contrôleur appeléeeeeeeeeeee");
            messagingTemplate.convertAndSend( "/topic/notification/"+userId, message);
        
       }


}
