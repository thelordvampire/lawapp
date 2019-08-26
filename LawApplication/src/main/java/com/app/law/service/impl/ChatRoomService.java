package com.app.law.service.impl;

import com.app.law.constant.RoomStatus;
import com.app.law.entity.ChatRoom;
import com.app.law.repository.ChatRoomRepository;
import com.app.law.service.IChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class ChatRoomService implements IChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepo;

    @Override
    public ChatRoom createChatRoom(String clientUserName) {
        ChatRoom room = new ChatRoom();
        room.setClientUserName(clientUserName);
        room.setStartDate(Instant.now());
        room.setStatus(RoomStatus.NEW);

        return chatRoomRepo.save(room);
    }

    @Override
    public List<ChatRoom> getAllNewChatRoom() {
        return chatRoomRepo.getAllRoomByStatus(RoomStatus.NEW);
    }

    @Override
    public ChatRoom addServerUser(Integer chatRoomId, Integer serverUserId) {
        if(chatRoomId == null || serverUserId == null)
            throw new NullPointerException("id null");

        ChatRoom chatRoom = chatRoomRepo.getOne(chatRoomId);
        chatRoom.setServerUserId(serverUserId);
        chatRoom.setStatus(RoomStatus.SERVER_JOIN);
        return chatRoomRepo.save(chatRoom);
    }

    @Override
    public ChatRoom closeRoom(Integer roomId) {
        ChatRoom chatRoom = chatRoomRepo.getOne(roomId);
        chatRoom.setEndDate(Instant.now());
        chatRoom.setStatus(RoomStatus.END);

        return chatRoomRepo.save(chatRoom);
    }
}