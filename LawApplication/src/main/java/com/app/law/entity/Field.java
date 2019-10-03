package com.app.law.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "field")
@Table(name = "field")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field implements Serializable {

    private static final long serialVersionUID = 153L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}