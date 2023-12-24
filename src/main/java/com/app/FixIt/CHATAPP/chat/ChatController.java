package com.app.FixIt.CHATAPP.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.FixIt.CHATAPP.chatRoom.ChatRoomRepository;
import com.app.FixIt.CHATAPP.chatRoom.ChatRoomService;
import com.app.FixIt.DTO.Maintenance.TacheDto;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.User;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController{
     
    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    TachesRepository tachesRepository;
    
    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired 
    ChatNotificationRepository notificationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MaintenancierRepository maintenancierRepository;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage){
        ChatMessageService chatMessageService = new ChatMessageService(chatMessageRepository, chatRoomService, chatRoomRepository);
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        System.out.println("===========================================");
        System.out.println("svdMgsssss===========================================");
        System.out.println(savedMsg.getContent());
        System.out.println("===========================================");
        messagingTemplate.convertAndSendToUser(
            chatMessage.getRecipientId(),
            "/queue/messages",
            new ChatNotification(
                savedMsg.getId(),
                savedMsg.getSenderId(),
                savedMsg.getRecipientId(),
                savedMsg.getContent(),
                savedMsg.getTaskId()
            )
        );
        System.out.println(messagingTemplate.getUserDestinationPrefix()+"===========================================");

    }

    @GetMapping("/messages/{senderId}/{recipientId}/{taskId}")

    public ResponseEntity<List<ChatMessage>>  findChatMessages(@PathVariable String senderId, @PathVariable String recipientId, @PathVariable Long taskId){
        ChatMessageService chatMessageService = new ChatMessageService(chatMessageRepository, chatRoomService, chatRoomRepository);
        List<ChatMessage> messages = chatMessageService.findChatMessages(senderId, recipientId);
        List<ChatMessage> taskMessages = new ArrayList<>();
        for (ChatMessage chatMessage : messages) {
            if(chatMessage.getTaskId() == taskId){
                taskMessages.add(chatMessage);
            }
        }
        System.out.println("pito======================================================");
        System.out.println(taskMessages.size());
        System.out.println(messages.size());
        return ResponseEntity.ok(taskMessages);
    }

    //A supprimer
    @GetMapping("/task/{Id}")
    public TacheDto findTache(@PathVariable Long Id, HttpSession session){

        Taches tache = tachesRepository.findById(Id).get();
        TacheDto tacheDto = new TacheDto();
        String clientUsername = (String)session.getAttribute("username");
        boolean IsClient = clientUsername == tache.getClient().getUsername();
        if(IsClient){
            tacheDto.setSelectedUsername(tache.getMaintenancier().getUsername());
            tacheDto.setClientUsername(clientUsername);
        }
        else{
            tacheDto.setSelectedUsername(clientUsername);
            tacheDto.setClientUsername(tache.getMaintenancier().getUsername());
        }
        //On pourra mettre plus d'information dant le Dto au besoin
        return tacheDto;
    }

    @GetMapping("/notification/{Id}")
    public Integer findNotification(@PathVariable Long Id, HttpSession session){
        Integer numberNotif = 0;
        List<ChatNotification> notifications =  notificationRepository.findByTaskId(Id);
        for (ChatNotification notification : notifications) {
            if(notification.getRecipientId() == (String)session.getAttribute("username")){
                numberNotif = numberNotif + 1;
            }
        }
        return numberNotif;
    }

    @GetMapping("/pageChat")
    public String pageChat(){
        return "HTML/chat";
    }

    @GetMapping("/loadTask")
    @ResponseBody
    public List<TacheDto> LoadTask(HttpSession session){
        System.out.println("=============================================================");
        System.out.println("=============================================================");

        Long id = (Long) session.getAttribute("id");
       
        if(id != null){
                Optional<Client> optuser = clientRepository.findById(id);
                User user = null;
                List<Taches> taches =null; 

                if(optuser.isPresent()){
                    user = optuser.get();
                    taches = tachesRepository.findAllByClient((Client)user);
                }
                else{
                    user = maintenancierRepository.findById(id).get();
                    taches = tachesRepository.findAllByMaintenancier((Maintenancier)user);
                }
                String clientUsername = user.getUsername();
                System.out.println(clientUsername);
                List<TacheDto> tasks = new ArrayList<>();
                for (Taches tache : taches) {
                    TacheDto task = new TacheDto();
                    
                    task.setType(tache.getType());
                    task.setDescription(tache.getDescription());
                    task.setPhoto(tache.getImage());
                   
                    boolean IsClient = clientUsername == tache.getClient().getUsername();
                    if(IsClient){
                        if(tache.getMaintenancier()!=null){
                            task.setSelectedUsername(tache.getMaintenancier().getUsername());
                        }
                        task.setClientUsername(clientUsername);
                    }
                    else{
                        task.setSelectedUsername(tache.getClient().getUsername());
                        if(tache.getMaintenancier()!=null){
                            task.setClientUsername(clientUsername);
                        }
                    }
                            System.out.println("=============================================================");
                            System.out.println(tache.getId());
                            System.out.println("=============================================================");

                    task.setId1(tache.getId());
                    task.setDate(tache.getDate());
                    task.setEtat(tache.getEtat());

                    tasks.add(task);
                }

                return tasks;

        }
        return null;
    }

    @GetMapping("/username")
    @ResponseBody
    public String username(HttpSession session){
        Long id = (Long)session.getAttribute("id");
        String username;
        Optional<Client> client = clientRepository.findById(id);
         System.out.println("isempty================================ " + id);
        System.out.println(client.isEmpty());
        if(client.isEmpty()){
            username = maintenancierRepository.findById(id).get().getUsername();
            System.out.println("username================================");
            System.out.println(username);
            return username;
        }else{
                username = client.get().getUsername();
                System.out.println("username================================");
                System.out.println(username);
                return username;
        }

  
    }
}

