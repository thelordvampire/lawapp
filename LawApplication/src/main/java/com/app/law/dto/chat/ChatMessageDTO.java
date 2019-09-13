/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.law.dto.chat;

import com.app.law.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author bao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    
    private MessageType type;
    private String content;
    private String sender;

    private Integer roomId;
    private Integer serverUserId;
    private String serverUserName;
    
}
