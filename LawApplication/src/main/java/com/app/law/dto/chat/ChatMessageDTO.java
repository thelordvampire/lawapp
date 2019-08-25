/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.dto.chat;

import lombok.Data;

/**
 *
 * @author bao
 */
@Data
public class ChatMessageDTO {
    
    private MessageType type;
    private String content;
    private String sender;

    private Integer roomId;
    private Integer serverUserId;
    private String serverUserName;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    
}
