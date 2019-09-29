package com.app.law.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by https://github.com/kwanpham
 */
@Data
@Entity(name = "post")
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 153887L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @NotNull
    @Column(name = "image", columnDefinition = "MEDIUMTEXT")
    private String image;

    @NotNull
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @NotNull
    @Column(name = "short_content")
    private String shortContent;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @Column(name = "is_own")
    private Boolean owner;

    @Column(name = "tag")
    private String tag;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REFRESH)
    @Transient
    private User user;

    @Column(name =  "post_status")
    private String status;

    @Column(name = "created_datetime")
    @CreationTimestamp
    private Timestamp createdDatetime;

    @Column(name = "updated_datetime")
    @UpdateTimestamp
    private Timestamp updatedDatetime;
}
