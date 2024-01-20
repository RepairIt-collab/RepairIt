package com.app.FixIt.CHATAPP.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatNotificationRepository extends JpaRepository<ChatNotification, Long>{

    List<ChatNotification> findByTaskId(Long id);

}
