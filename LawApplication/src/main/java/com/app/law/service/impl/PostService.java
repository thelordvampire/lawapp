package com.app.law.service.impl;

import com.app.law.constant.PostStatus;
import com.app.law.dto.PostDto;
import com.app.law.entity.Post;
import com.app.law.entity.User;
import com.app.law.mapper.PostMapper;
import com.app.law.repository.PostRepository;
import com.app.law.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by https://github.com/kwanpham
 */
@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository pr;

    @Override
    public Post save(PostDto dto) {
        Post post = PostMapper.DtoToEntity(dto);
        post.setStatus(PostStatus.PENDING);

        return pr.save(post);
    }

    @Override
    public Post update(PostDto dto) {
        Post post = PostMapper.DtoToEntity(dto);
        post.setStatus(PostStatus.PENDING);
        return pr.save(post);
    }

    @Override
    public List<PostDto> findAllByStatus(String status) {
        List<Post> postList = pr.findAllByStatus(status);
        List<PostDto> postDtoList = postList.stream()
                .map(entity -> PostMapper.EntityToDto(entity))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public Post findById(long id) {
        Optional<Post> post = pr.findById(id);
        return post.orElse(null);
    }

    @Override
    public List<PostDto> findAll() {
        return pr.findAll().stream()
                .map(entity -> PostMapper.EntityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return  pr.findAll(pageable).map(PostMapper::EntityToDto);

    }

    @Override
    public boolean updateStatus(Long id , String status) {
        try {
            Optional<Post> optionalPost = pr.findById(id);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setStatus(status);
                pr.save(post);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }

    }

    void deletePost(long id) {
        pr.deleteById(id);
    }
}
