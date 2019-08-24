package com.app.law.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "role_privilege")
@Data
public class Role_Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "privilege_id", insertable = false, updatable = false)
    private Integer privilegeId;

    @OneToOne
    private Privilege privilege;

}
