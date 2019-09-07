package com.app.law.dto;

import com.app.law.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by https://github.com/kwanpham
 */
@Data
public class PostDto {


    private Long id;

    @Length(max = 50 , min = 4)
    private String title;

    private String content;

    private String tag;

    private User user;

    private String status;

    private boolean isDelete;

    private Timestamp createdDatetime;

    private Timestamp updatedDatetime;
}
