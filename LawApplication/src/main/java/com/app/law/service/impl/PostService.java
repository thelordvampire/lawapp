package com.app.law.service.impl;

import com.app.law.constant.PostStatus;
import com.app.law.entity.Post;
import com.app.law.repository.PostRepository;
import com.app.law.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kwanpham
 */
@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository pr;

    @Override
    public Post save(Post post) {
        post.setStatus(PostStatus.PENDING);
        return pr.save(post);
    }

    @Override
    public Post update(Post post) {
        post.setStatus(PostStatus.PENDING);
        return pr.save(post);
    }

    @Override
    public List<Post> findAllByStatus(String status) {
        return pr.findAllByStatus(status);
    }

    @Override
    public Post findById(long id) {
        Optional<Post> post = pr.findById(id);
        return post.orElse(null);
    }

    @Override
    public List<Post> findAll() {
        return pr.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return pr.findAll(pageable);
    }

    @Override
    public boolean updateStatus(Long id , String status) {
        try {
            Optional<Post> optionalPost = pr.findById(id);
            Post post = optionalPost.get();
            post.setStatus(status);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    void deletePost(long id) {
        pr.deleteById(id);
    }
}
