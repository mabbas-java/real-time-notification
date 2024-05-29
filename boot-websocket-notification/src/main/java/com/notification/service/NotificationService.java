package com.notification.service;

import com.notification.model.Notification;
import com.notification.repostory.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {
private final NotificationRepository notificationRepository;
    public void saveNotification(Notification notification){
        com.notification.entity.Notification notification1 = new com.notification.entity.Notification();
        notification1.setMessage(notification.getMessage());
        notificationRepository.save(notification1);
    }
    public void updateNotification(Notification notification,Long id){
        Optional<com.notification.entity.Notification> notification1 =notificationRepository.findById(id);
        if(notification1.isPresent()){
            notification1.get().setMessage(notification.getMessage());
            notificationRepository.save(notification1.get());
        }

    }


}
