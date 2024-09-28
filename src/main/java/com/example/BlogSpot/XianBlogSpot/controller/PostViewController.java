package com.example.BlogSpot.XianBlogSpot.controller;

import org.springframework.ui.Model;
import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.model.User;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostViewController {

    @Autowired
    private PostService postService;

    // Esto busca en templates/los html necesarios
    @GetMapping("/posts")
    public String showPostsPage() {
        return "post/posts";
    }

    // Mostrar el formulario para crear un nuevo post
    @GetMapping("/posts/new")
    public String showNewPostForm() {
        return "newPost/new"; // Asegúrate de que esta plantilla exista
    }

    @PostMapping("/posts/create")
    public String createPostAndRedirect(@ModelAttribute Post post) {
        // Guardar el post y redirigir
        postService.savePost(post);
        return "redirect:/posts"; // Redirige a la lista de posts después de guardar
    }

    @GetMapping("/detail/{id}")
    public String showPostDetail(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "postDetail/postDetail";
    }
}
