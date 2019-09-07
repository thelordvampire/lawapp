package com.app.law.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Table(name = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String repassword;

    @Column(name = "name")
    private String name;

    @Column(name = "image", columnDefinition = "MEDIUMTEXT")
    private String image;

    @Column(name = "roleId")
    private Integer roleId;

    @OneToMany(mappedBy = "userId")
    private List<User_Privilege> listUserPrivilege;

    @OneToMany(mappedBy = "userId")
    private List<User_Specialization> listUserSpecialization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Post> listPost = new HashSet<>();

    @Transient
    private String token;

    @Transient
    private List<Privilege> listPrivilege;

    public User() {
    }

//    public User(String username, String password, String email, String name) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.name = name;
//    }

    public User(String password, String email, String name, Integer roleId, List<Privilege> listPrivilege) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.listPrivilege = listPrivilege;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User"+" ,"+
                "id="+id+" ,"+
                "email="+email+" ,"+
                "password="+password+" ,"+
                "token="+token+" ,"+
                "name="+name;
    }

}