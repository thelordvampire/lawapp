package com.app.law.service.impl;

import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.entity.ChatMessage;
import com.app.law.entity.User;
import com.app.law.repository.ChatMessageRepository;
import com.app.law.service.IChatMessageService;
import com.app.law.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChatMessageService implements IChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepo;

    @Autowired
    private IUserService userService;

    @Override
    public ChatMessageDTO saveMessage(ChatMessageDTO messageDTO) {
        ChatMessage message = new ChatMessage();
        message.setRoomId(messageDTO.getRoomId());
        message.setContent(messageDTO.getContent());
        message.setCreatedDate(Instant.now());
        if(messageDTO.getServerUserId()!= null) {
            User user = userService.findUserById(messageDTO.getServerUserId());
            message.setUser(user);
            message.setSender(user.getName());
        } else {
            message.setSender(messageDTO.getSender());
        }
        chatMessageRepo.save(message);
        return messageDTO;
    }

    @Override
    public List<ChatMessage> getAllMessageByRoomID(Integer roomId) {
        return chatMessageRepo.findByRoomId(roomId);
    }
}
