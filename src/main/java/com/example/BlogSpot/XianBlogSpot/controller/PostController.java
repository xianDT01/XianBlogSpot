package com.example.BlogSpot.XianBlogSpot.controller;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.model.User;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class    PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Obtener todos los posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Obtener un post específico
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }
}
