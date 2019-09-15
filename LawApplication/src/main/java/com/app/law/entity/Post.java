package com.app.law.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by https://github.com/kwanpham
 */
@Data
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "tag")
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "created_datetime")
    @CreationTimestamp
    private Date createdDatetime;
    @Column(name = "updated_datetime")
    @UpdateTimestamp
    private Date updatedDatetime;
}
