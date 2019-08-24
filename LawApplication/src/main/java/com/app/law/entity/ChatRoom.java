package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity(name = "chat_room")
@Table(name = "chat_room")
@Data
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "clientUserName", nullable = false)
    private String clientUserName;

    @Column(name = "serverUserId", nullable = false)
    private Integer serverUserId;

    @Column(name = "startDate")
    private Instant startDate;

    @Column(name = "endDate")
    private Instant endDate;

    @OneToMany(mappedBy = "roomId")
    private List<ChatMessage> listChatMessage;

}