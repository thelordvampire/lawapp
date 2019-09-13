package com.app.law.service.impl;

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
        return pr.save(post);
    }

    @Override
    public Post update(Post post) {
       return pr.save(post);
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

    void deletePost(long id) {
        pr.deleteById(id);
    }
}
