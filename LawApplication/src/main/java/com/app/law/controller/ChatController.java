/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.controller;

import com.app.law.constant.MessageType;
import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.dto.chat.ChatRoomDTO;
import com.app.law.entity.ChatMessage;
import com.app.law.entity.ChatRoom;
import com.app.law.entity.User;
import com.app.law.service.IChatMessageService;
import com.app.law.service.IChatRoomService;
import com.app.law.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author bao
 */
@Controller
public class ChatController {

    @Autowired
    private IChatRoomService chatRoomService;

    @Autowired
    private IChatMessageService chatMessageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @RequestMapping(value = "/chat/room/get-all")
    public ResponseEntity<List<ChatMessage>> getAllChatMessage(@NotNull Integer roomId) {
        List<ChatMessage> allMessageByRoomID = chatMessageService.getAllMessageByRoomID(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(allMessageByRoomID);
    }
    
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable Integer roomId, @Payload ChatMessageDTO chatMessageDTO) {
        chatMessageDTO = chatMessageService.saveMessage(chatMessageDTO);
        messagingTemplate.convertAndSend(String.format("/topic/%s", roomId), chatMessageDTO);
    }

    @MessageMapping("/chat/{roomId}/addUser")
//    @SendTo("/topic/public")
    public ChatMessageDTO addUser(@DestinationVariable Integer roomId, @Payload ChatMessageDTO chatMessageDTO,
                                  SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        Integer currentRoomId = (Integer) headerAccessor.getSessionAttributes().put("room_id", roomId);

        if (currentRoomId != null) {
            ChatMessageDTO leaveMessage = new ChatMessageDTO();
            leaveMessage.setType(MessageType.LEAVE);
            leaveMessage.setSender(chatMessageDTO.getSender());
            messagingTemplate.convertAndSend(String.format("/topic/%s", currentRoomId), leaveMessage);
        }
        if(chatMessageDTO.getServerUserId()!= null) {
            User user = userService.findUserById(chatMessageDTO.getServerUserId());
            headerAccessor.getSessionAttributes().put("username", user.getName());
            chatMessageDTO.setServerUserName(user.getName());
            chatRoomService.addServerUser(roomId, user.getId());
        } else {
            headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getSender());
        }
        messagingTemplate.convertAndSend(String.format("/topic/%s", roomId), chatMessageDTO);
        return chatMessageDTO;
    }

    @RequestMapping(value = "/chat/room/create")
    public ResponseEntity<ChatRoomDTO> createRoom(@Payload ChatRoomDTO chatRoomDTO) {
//        headerAccessor.getSessionAttributes().put("username", chatRoomDTO.getClientUserName());
        ChatRoom chatRoom = chatRoomService.createChatRoom(chatRoomDTO.getClientUserName());

        chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.setId(chatRoom.getId());
        chatRoomDTO.setClientUserName(chatRoom.getClientUserName());

        return ResponseEntity.status(HttpStatus.OK).body(chatRoomDTO);
    }

    @RequestMapping(value = "/chat/room/get-new")
    public ResponseEntity<List<ChatRoom>> getAllNewChatRoom() {
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomService.getAllNewChatRoom());
    }


    
}
