package com.app.law.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_field")
@Data
public class User_Field implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "field_id", insertable = false, updatable = false)
    private Integer fieldId;

    @OneToOne
    private Field field;
}
