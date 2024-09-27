package com.example.BlogSpot.XianBlogSpot.controller;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.model.User;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class PostApiController {
    private final PostService postService;
    @Autowired
    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    // Obtener todos los posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Crear un nuevo post
    @PostMapping("/create")
    public ResponseEntity<Void> createPost(@ModelAttribute("post") Post post) {
        User author = getAuthenticatedUser();
        post.setAuthor(author);
        postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // Simular la obtención del usuario autenticado
    private User getAuthenticatedUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Xian");
        return user;
    }
}
