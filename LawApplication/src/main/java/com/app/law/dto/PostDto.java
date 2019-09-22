package com.app.law.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
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

    private String image;

    private String tag;

    private Integer userId;

    private String status;

    private Timestamp createdDatetime;

    private Timestamp updatedDatetime;
}
