package com.app.law.service;

import com.app.law.dto.chat.ChatMessageDTO;
import com.app.law.entity.ChatMessage;
import java.util.List;

public interface IChatMessageService {

    ChatMessageDTO saveMessage(ChatMessageDTO messageDTO);

    List<ChatMessageDTO> getAllMessageByRoomID(Integer roomId);
}
