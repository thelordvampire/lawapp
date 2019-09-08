package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

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
    private String type;

    @Column(name = "createdDate")
    private Instant createdDate;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "sender")
    private String sender;

    @OneToOne
    private User user;

    @Column(name = "roomId")
    private Integer roomId;

}