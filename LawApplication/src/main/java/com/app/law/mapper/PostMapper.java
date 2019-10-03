package com.app.law.mapper;

import com.app.law.dto.PostDto;
import com.app.law.entity.Post;
import com.app.law.entity.User;

/**
 * Created by https://github.com/kwanpham
 */
public class PostMapper {

    public static Post DtoToEntity(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setShortContent(dto.getShortContent());
        post.setImage(dto.getImage());
        post.setUserId(dto.getUserId());
        post.setOwner(dto.getOwner());
//        post.setTag(dto.getTag());
        return post;
    }

    public static PostDto EntityToDto(Post entity) {
        PostDto dto = new PostDto();
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        System.out.println(entity.getContent());
        dto.setShortContent(entity.getShortContent());
        dto.setImage(entity.getImage());
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setOwner(entity.getOwner());
//        dto.setTag(entity.getTag());
        dto.setCreatedDatetime(entity.getCreatedDatetime());
        dto.setUpdatedDatetime(entity.getUpdatedDatetime());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}
