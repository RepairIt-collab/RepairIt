package com.app.FixIt.CHATAPP.chat;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.app.FixIt.CHATAPP.chatRoom.ChatRoomRepository;
import com.app.FixIt.CHATAPP.chatRoom.ChatRoomService;


@Service
public class ChatMessageService{

    private  ChatMessageRepository repository;
    private  ChatRoomService chatRoomService;


    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService, ChatRoomRepository chatRoomRepository){
        this.chatRoomService = chatRoomService;
        this.repository = chatMessageRepository;
        this.chatRoomService.setChatRoomRepository(chatRoomRepository);
    }
    
    public ChatMessage save(ChatMessage chatMessage){
        String chatId = chatRoomService
                        .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                        .orElseThrow();
        chatMessage.setChat(chatId);
        repository.save(chatMessage);
        repository.findByChatId(chatId);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());

    }

}