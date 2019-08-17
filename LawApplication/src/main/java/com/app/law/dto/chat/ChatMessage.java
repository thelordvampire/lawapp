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
public class ChatMessage {
    
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    
}
