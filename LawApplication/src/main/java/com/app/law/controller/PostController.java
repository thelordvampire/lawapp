package com.app.law.controller;

import com.app.law.entity.Post;
import com.app.law.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
@RestController
public class PostController {

    @Autowired
    private IPostService postService;

    // Lấy bài viết theo id
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id" ,required = true)  Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    //Lất tất cả bài viết
    @GetMapping("/posts")
    public ResponseEntity<List> getAllPost() {
        return ResponseEntity.ok(postService.findAll());
    }

    //Update status post
    @PostMapping("/post/status")
    public ResponseEntity<String> updatePostStatus(@RequestParam(name = "id" , required = true) Long id ,
                                                 @RequestParam(name = "status" , required = true) String status){
        if (postService.updateStatus(id , status) ){
            return ResponseEntity.ok("true");
        } else return ResponseEntity.ok("false");
    }

    // Đăng bài nhưng set status pending
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }

    // Update nhưng set status pending
    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }


}