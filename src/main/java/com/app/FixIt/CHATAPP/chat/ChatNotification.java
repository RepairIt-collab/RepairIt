package com.app.FixIt.CHATAPP.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private Long taskId;

   
    public ChatNotification(Long id, String senderId, String recipientId, String content, Long taskId){
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.recipientId = recipientId;
        this.taskId = taskId;
    }

    public ChatNotification(){
        
    }

    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }               
    public String getContent() {
        return content;
    }
    public Long getId() {
        return id;
    }
    public String getRecipientId() {
        return recipientId;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setContent(String content) {
        this.content = content;
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
