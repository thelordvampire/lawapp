package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "chat_message")
@Table(name = "chat_message")
@Data
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private MessageType type;

    @Column(name = "roomId")
    private Integer roomId;

    enum MessageType {
        TEXT, IMAGE
    }
}