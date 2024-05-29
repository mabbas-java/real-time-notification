package com.notification.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CustomWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    private final AtomicInteger activeSessions = new AtomicInteger();

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {

            @Override
            public void afterConnectionEstablished(org.springframework.web.socket.WebSocketSession session) throws Exception {
                activeSessions.incrementAndGet();
                System.out.println("New connection established. Total connections: " + activeSessions.get());
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(org.springframework.web.socket.WebSocketSession session, CloseStatus closeStatus) throws Exception {
                activeSessions.decrementAndGet();
                System.out.println("Connection closed. Total connections: " + activeSessions.get());
                super.afterConnectionClosed(session, closeStatus);
            }

            @Override
            public void handleTransportError(org.springframework.web.socket.WebSocketSession session, Throwable exception) throws Exception {
                System.err.println("Transport error: " + exception.getMessage());
                super.handleTransportError(session, exception);
            }
        };
    }

    public int getActiveSessions() {
        return activeSessions.get();
    }
}

