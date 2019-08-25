/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.config;

import com.app.law.constant.MessageType;
import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.service.IChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 *
 * @author bao
 */
@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private IChatRoomService roomService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        Integer roomId = (Integer) headerAccessor.getSessionAttributes().get("room_id");
        if(username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
            chatMessageDTO.setType(MessageType.LEAVE);
            chatMessageDTO.setSender(username);

            roomService.closeRoom(roomId);
            messagingTemplate.convertAndSend(String.format("/topic/%d", roomId), chatMessageDTO);
        }
    }
}