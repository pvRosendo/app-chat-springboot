package com.rosendo.app_chat.infrastructure.config;

import java.util.logging.Logger;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.rosendo.app_chat.domain.enums.MessageType;
import com.rosendo.app_chat.domain.models.ChatMessageModel;

@Component
public class WebSocketEventListener {

  private final SimpMessageSendingOperations messagingTemplate;
  private final Logger logger = Logger.getLogger(WebSocketEventListener.class.getName());

  public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
        logger.info("User disconnected: " + username);
        ChatMessageModel chatMessage = new ChatMessageModel.Builder()
                .type(MessageType.LEAVE)
                .sender(username)
                .build();
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
  }
}
