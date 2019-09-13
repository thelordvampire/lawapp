package com.app.law.controller;

import com.app.law.entity.Post;
import com.app.law.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by https://github.com/kwanpham
 */
@RestController
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }


    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }

    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }


}