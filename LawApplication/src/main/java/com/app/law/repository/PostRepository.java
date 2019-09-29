package com.app.law.repository;

import com.app.law.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
public interface PostRepository extends JpaRepository<Post , Long> {

    List<Post> findAllByStatus(String status);

    List<Post> findAllByStatusAndOwnerTrue(String status);

    List<Post> findAllByStatusAndOwnerFalse(String status);

    @Query(value = "select p from post p where p.status = ?1 order by p.updatedDatetime desc")
    List<Post> findAllByStatusWithLimit(String status, Pageable pageable);

}
