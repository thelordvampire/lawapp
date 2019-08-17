package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3983459089478748068L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "type", nullable = false)
    private String type;

}