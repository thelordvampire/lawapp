package com.app.law.service.impl;

import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.entity.ChatMessage;
import com.app.law.entity.User;
import com.app.law.entity.mapper.ChatMessageMapper;
import com.app.law.repository.ChatMessageRepository;
import com.app.law.service.IChatMessageService;
import com.app.law.service.IUserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService implements IChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepo;

    @Autowired
    private IUserService userService;

    private final ChatMessageMapper chatMessageMapper = Mappers.getMapper(ChatMessageMapper.class);

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
//            messageDTO.setServerUserImage(user.getImage());
        } else {
            message.setSender(messageDTO.getSender());
        }
        chatMessageRepo.save(message);
        return messageDTO;
    }

    @Override
    public List<ChatMessageDTO> getAllMessageByRoomID(Integer roomId) {
        List<ChatMessage> listChatMessage = chatMessageRepo.findByRoomId(roomId);
        List<ChatMessageDTO> listChatMessageDTO = null;
        if(listChatMessage!= null) {
            listChatMessageDTO = new ArrayList<>();
            for (ChatMessage chatMessage : listChatMessage) {
                listChatMessageDTO.add(chatMessageMapper.toDTO(chatMessage));
            }
        }

        return listChatMessageDTO;
    }
}
