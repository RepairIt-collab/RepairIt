package com.app.FixIt.CHATAPP.chatRoom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String chatId;
    private String senderId;
    private String recipientId;



    public ChatRoom(){
        
    }


    public ChatRoom(String chatId, String senderId, String recipientId){
        this.chatId = chatId;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }






    
    
    public Long getId() {
        return id;
    }
    public String getChatId() {
        return chatId;
    }
    public String getRecipientId() {
        return recipientId;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
