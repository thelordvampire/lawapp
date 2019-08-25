package com.app.law.service.impl;

import com.app.law.entity.ChatRoom;
import com.app.law.repository.ChatRoomRepository;
import com.app.law.service.IChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.time.Instant;

@Service
public class ChatRoomService implements IChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepo;

    @Override
    public ChatRoom createChatRoom(String clientUserName) {
        ChatRoom room = new ChatRoom();
        room.setClientUserName(clientUserName);
        room.setStartDate(Instant.now());

        return chatRoomRepo.save(room);
    }

    @Override
    public ChatRoom addServerUser(Integer chatRoomId, Integer serverUserId) {
        if(chatRoomId == null || serverUserId == null)
            throw new NullPointerException("id null");

        ChatRoom chatRoom = chatRoomRepo.getOne(chatRoomId);
        chatRoom.setServerUserId(serverUserId);
        return chatRoomRepo.save(chatRoom);
    }
}
