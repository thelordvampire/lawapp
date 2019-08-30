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
import com.app.law.service.IUserService;
import com.app.law.service.IChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 *
 * @author bao
 */
@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private IChatRoomService chatRoomService;

    @Autowired
    private IChatMessageService chatMessageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @RequestMapping(value = "/chat/room/{roomId}/get-all")
    public ResponseEntity<List<ChatMessage>> getAllChatMessage(@PathVariable Integer roomId) {
        List<ChatMessage> allMessageByRoomID = chatMessageService.getAllMessageByRoomID(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(allMessageByRoomID);
    }
    
    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable Integer roomId, @Payload ChatMessageDTO chatMessageDTO) {
        System.out.println("vao send Message roi");
        chatMessageDTO = chatMessageService.saveMessage(chatMessageDTO);
        messagingTemplate.convertAndSend(String.format("/topic/%d", roomId), chatMessageDTO);
    }

    @MessageMapping("/chat/{roomId}/sendType")
    public void sendType(@DestinationVariable Integer roomId, @Payload ChatMessageDTO chatMessageDTO) {
        logger.debug("vao send type roi");
        messagingTemplate.convertAndSend(String.format("/topic/%d", roomId), chatMessageDTO);
    }

    @MessageMapping("/chat/{roomId}/addUser")
//    @SendTo("/topic/public")
    public ChatMessageDTO addUser(@DestinationVariable Integer roomId,
        @Payload ChatMessageDTO chatMessageDTO,
        SimpMessageHeaderAccessor headerAccessor)
    {
        logger.debug("chat room add user with room: {} and user id: {}", roomId, chatMessageDTO.getServerUserId());
        // Add username in web socket session
        Integer currentRoomId = (Integer) headerAccessor.getSessionAttributes().put("room_id", roomId);

        if (currentRoomId != null) {
            ChatMessageDTO leaveMessage = new ChatMessageDTO();
            leaveMessage.setType(MessageType.LEAVE);
            leaveMessage.setSender(chatMessageDTO.getSender());
            messagingTemplate.convertAndSend(String.format("/topic/%d", currentRoomId), leaveMessage);
        }
        if(chatMessageDTO.getServerUserId()!= null) {
            User user = userService.findUserById(chatMessageDTO.getServerUserId());
            headerAccessor.getSessionAttributes().put("username", user.getName());
            chatMessageDTO.setServerUserName(user.getName());
            chatMessageDTO.setSender(user.getName());
            chatRoomService.addServerUser(roomId, user.getId());
        } else {
            headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getSender());
        }
        messagingTemplate.convertAndSend(String.format("/topic/%d", roomId), chatMessageDTO);
        return chatMessageDTO;
    }

    @RequestMapping(value = "/chat/room/create", method = RequestMethod.POST)
    public ResponseEntity<ChatRoomDTO> createRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(chatRoomDTO.getClientUserName());
        chatRoomDTO.setId(chatRoom.getId());
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomDTO);
    }

    @RequestMapping(value = "/chat/room/get-new")
    public ResponseEntity<List<ChatRoom>> getAllNewChatRoom() {
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomService.getAllNotFinishChatRoom());
    }
    
}
