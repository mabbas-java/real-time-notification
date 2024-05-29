package com.notification.controller;

import com.notification.model.Notification;
import com.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebSocketController {
    private final NotificationService notificationService;
    @MessageMapping("/send")
    @SendTo("/topic/chat")
    public void sendMessage(Notification message) {
        notificationService.saveNotification(message);
    }
}
