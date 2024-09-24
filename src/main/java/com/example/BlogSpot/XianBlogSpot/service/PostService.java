package com.example.BlogSpot.XianBlogSpot.service;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    // Inyección de dependencias a través del constructor
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Método para obtener todos los posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Método para guardar un nuevo post
    public Post savePost(Post post) {
        return postRepository.save(post);
    }


}
