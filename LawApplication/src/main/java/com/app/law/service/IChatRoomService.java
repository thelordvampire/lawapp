package com.app.law.service;

import com.app.law.entity.ChatRoom;

public interface IChatRoomService {

    ChatRoom createChatRoom(String clientUserName);

    ChatRoom addServerUser(Integer chatRoomId, Integer serverUserId) throws NullPointerException;
}
