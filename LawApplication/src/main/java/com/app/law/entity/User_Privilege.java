package com.app.law.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_privilege")
@Data
public class User_Privilege implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "privilege_id", insertable = false, updatable = false)
    private Integer privilegeId;

    @OneToOne
    private Privilege privilege;

}
