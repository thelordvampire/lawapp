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
import org.springframework.data.domain.PageRequest;
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
    private PostRepository postRepo;

    @Override
    public Post save(PostDto dto) {
        Post post = PostMapper.DtoToEntity(dto);
        post.setStatus(PostStatus.PENDING);
        return postRepo.save(post);
    }

    @Override
    public Post update(PostDto dto) {
        Post post = PostMapper.DtoToEntity(dto);
        post.setStatus(PostStatus.PENDING);
        return postRepo.save(post);
    }

    @Override
    public List<PostDto> findAllByStatus(String status) {
        List<Post> postList = postRepo.findAllByStatus(status);
        List<PostDto> postDtoList = postList.stream()
            .map(PostMapper::EntityToDto)
            .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByLimit(Integer limit) {
        PageRequest pageRequest = PageRequest.of(0, limit);
        List<Post> postList = postRepo.findAllByStatusWithLimit(PostStatus.APPROVED, pageRequest);
        List<PostDto> postDtoList = postList.stream()
                .map(PostMapper::EntityToDto)
                .collect(Collectors.toList());
        return postDtoList;

    }

    @Override
    public Post findById(long id) {
        Optional<Post> post = postRepo.findById(id);
        return post.orElse(null);
    }

    @Override
    public List<PostDto> findAll() {
        return postRepo.findAll().stream()
            .map(PostMapper::EntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostOwner() {
        return postRepo.findAllByStatusAndOwnerTrue(PostStatus.PENDING).stream()
            .map(PostMapper::EntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostOther() {
        return postRepo.findAllByStatusAndOwnerFalse(PostStatus.PENDING).stream()
            .map(PostMapper::EntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return  postRepo.findAll(pageable).map(PostMapper::EntityToDto);
    }

    @Override
    public boolean updateStatus(Long id , String status) {
        try {
            Optional<Post> optionalPost = postRepo.findById(id);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setStatus(status);
                postRepo.save(post);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }

    }

    void deletePost(long id) {
        postRepo.deleteById(id);
    }
}
