package com.app.law.controller;

import com.app.law.dto.PostDto;
import com.app.law.entity.Post;
import com.app.law.mapper.PostMapper;
import com.app.law.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
@RestController
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private IPostService postService;

    // Lấy bài viết theo id
    @GetMapping("/post/{id}")
    public ResponseEntity getPostById(@PathVariable(value = "id" ,required = true)  Long id) {
        Post post = postService.findById(id);
        if(post != null) {
            return ResponseEntity.ok(PostMapper.EntityToDto(post)) ;
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found post");
    }

    //Lất tất cả bài viết
    @GetMapping("/post/get-all")
    public ResponseEntity<List> getAllPost() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/post/get-limit/{limit}")
    public ResponseEntity<List> getPostByLimit(@PathVariable Integer limit) {
        return ResponseEntity.ok(postService.getPostByLimit(limit));
    }

    @GetMapping("/post/owner")
    public ResponseEntity<List> getAllPostOnwer() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/post/other")
    public ResponseEntity<List> getAllPostOther() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/paging")
    public ResponseEntity getAllPostPageing(@RequestParam(value = "page" , defaultValue = "0") int page,
                                                  @RequestParam(value = "size" , defaultValue = "10") int size) {
        return ResponseEntity.ok(postService.findAll(PageRequest.of(0 , 10)));
    }


    //Update status post
    @PostMapping("/post/status")
    public ResponseEntity updatePostStatus(@RequestParam(name = "id" , required = true) Long id ,
                                                 @RequestParam(name = "status" , required = true) String status){
        if (postService.updateStatus(id , status) ){
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }

    // Đăng bài nhưng set status pending
    @PostMapping("/post/create")
    public ResponseEntity createPost(@RequestBody PostDto dto) {
        try {
            this.postService.save(dto);
            log.info("post create success");
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            log.info("post create failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
        }

    }

    // Update nhưng set status pending
    @PutMapping("/post")
    public ResponseEntity updatePost(@RequestBody PostDto dto) {
        try {
            postService.save(dto);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
        }
    }


}