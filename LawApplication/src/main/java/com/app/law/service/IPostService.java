package com.app.law.service;

import com.app.law.dto.PostDto;
import com.app.law.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kwanpham
 */
public interface IPostService {

    Post save(PostDto dto);

    Post update(PostDto dto);

    List<PostDto> findAllByStatus(String status);

    List<PostDto> getPostByLimit(Integer limit);

    Post findById(long id);

    List<PostDto> findAll();

    List<PostDto> findPostOwner();

    List<PostDto> findPostOther();

    Page<PostDto> findAll(Pageable pageable);

    boolean updateStatus(Long id , String status);


}
