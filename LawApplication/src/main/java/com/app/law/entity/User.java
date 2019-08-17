package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user")
@Table(name = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String repassword;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    @Transient
    private String token;

    public User() {
    }

    @Override
    public String toString() {
        return "User"+" ,"+
                "id="+id+" ,"+
                "email="+email+" ,"+
                "username="+username+" ,"+
                "password="+password+" ,"+
                "token="+token+" ,"+
                "name="+name;
    }

}