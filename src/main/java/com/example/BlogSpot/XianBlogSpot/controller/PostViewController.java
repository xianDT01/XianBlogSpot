package com.example.BlogSpot.XianBlogSpot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PostViewController {
    @GetMapping("/posts")
    public String showPostsPage() {
        return "post/posts"; // Thymeleaf buscará el archivo en templates/post/posts.html
    }
}
