package com.app.law.repository;

import com.app.law.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
public interface PostRepository extends JpaRepository<Post , Long> {

    List<Post> findAllByStatus(String status);

}
