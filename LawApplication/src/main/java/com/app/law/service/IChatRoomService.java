package com.app.law.service;

import com.app.law.entity.ChatRoom;
import java.util.List;

public interface IChatRoomService {

    ChatRoom createChatRoom(String clientUserName);

    ChatRoom addServerUser(Integer chatRoomId, Integer serverUserId) throws NullPointerException;

    List<ChatRoom> getAllNewChatRoom();

    ChatRoom closeRoom(Integer id);
}
