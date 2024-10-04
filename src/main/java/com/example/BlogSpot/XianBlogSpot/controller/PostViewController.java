package com.example.BlogSpot.XianBlogSpot.controller;

import org.springframework.ui.Model;
import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostViewController {

    @Autowired
    private PostService postService;

    // Mostrar todos los posts
    @GetMapping("/posts")
    public String showPostsPage(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    // Mostrar el formulario para crear un nuevo post
    @GetMapping("/posts/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new Post()); // Para enlazar el formulario con un nuevo post
        return "newPost/new"; // Asegúrate de que esta plantilla exista
    }

    // Crear un nuevo post y redirigir
    @PostMapping("/posts/create")
    public String createPostAndRedirect(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts"; // Redirige a la lista de posts después de guardar
    }

    // Mostrar los detalles de un post
    @GetMapping("/detail/{id}")
    public String showPostDetail(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "postDetail/postDetail"; // Carga la plantilla HTML desde templates/postDetail
        } else {
            return "redirect:/posts";
        }
    }
    @GetMapping("/posts/edit")
    public String showEditPostPage() {
        return "editPost/editPost";
    }


}
