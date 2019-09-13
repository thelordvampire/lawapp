package com.app.law.service;

import com.app.law.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kwanpham
 */
public interface IPostService {

    Post save(Post post);

    Post update(Post post);

    Post findById(long id);

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);


}
