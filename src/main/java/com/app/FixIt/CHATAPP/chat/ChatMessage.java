package com.app.FixIt.CHATAPP.chat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import jakarta.persistence.Id;


@Entity
public class ChatMessage{
   
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private Long taskId;
    private LocalDate timestamp;

    public ChatMessage(){

    }


    public ChatMessage( String chat, String senderId, String recipientId, String content, LocalDate timestamp){
        this.chatId = chat;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = timestamp;
    }

















    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getChat() {
        return chatId;
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
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setChat(String chat) {
        this.chatId = chat;
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
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

}