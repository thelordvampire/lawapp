package com.app.law.repository;

import com.app.law.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by https://github.com/kwanpham
 */
public interface PostRepository extends JpaRepository<Post , Long> {

}
