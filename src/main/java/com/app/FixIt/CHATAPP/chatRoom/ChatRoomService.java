package com.app.FixIt.CHATAPP.chatRoom;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    
    private ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatRoomId(
        String senderId,
        String recipientId,
        boolean createNewRoomIfNotExists
    ){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                    .map(ChatRoom::getChatId)
                    .or(()->{
                        if(createNewRoomIfNotExists){
                           String chatId = createChatId(senderId, recipientId);
                           return Optional.of(chatId);
                        }
                        return Optional.empty();
                    });
                    

    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);
        System.out.println("=====================================ChatID");
        System.out.println("=====================================ChatID");
        System.out.println("=====================================ChatID");

        System.out.println(chatId);

        ChatRoom senderRecipient = new ChatRoom(chatId, senderId, recipientId);
        ChatRoom recipientSender = new ChatRoom(chatId, recipientId, senderId);
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }


    public void setChatRoomRepository(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    
}
