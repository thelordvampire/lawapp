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

        User user = new User();
        user.setId(dto.getUserId());
        post.setUser(user);
        post.setTag(dto.getTag());
        return post;
    }

    public static PostDto EntityToDto(Post entity) {
        PostDto dto = new PostDto();
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setTag(entity.getTag());
        dto.setCreatedDatetime(entity.getCreatedDatetime());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}
