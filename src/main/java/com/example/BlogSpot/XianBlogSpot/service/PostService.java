package com.example.BlogSpot.XianBlogSpot.service;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Obtener todos los posts
    public List<Post> getAllPosts() {
        return postRepository.findAll(); // Obtiene todos los posts de la base de datos
    }

    // Guardar un nuevo post
    public Post savePost(Post post) {
        Post savedPost = postRepository.save(post);
        System.out.println("Post guardado: " + savedPost);
        return savedPost;
    }

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null); // Manejar caso donde el post no existe
    }
}
