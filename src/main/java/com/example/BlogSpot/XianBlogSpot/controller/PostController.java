package com.example.BlogSpot.XianBlogSpot.controller;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.repository.PostRepository;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    private PostRepository postRepository;

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

    // Agregar un comentario
    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long postId, @RequestBody String comment) {
        // Lógica para agregar comentario
        return ResponseEntity.ok("Comentario agregado con éxito");
    }

    // Obtener posts con paginación
    @GetMapping("/paginated")
    public Page<Post> getAllPostsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable);
    }

    // Búsqueda de posts por palabra clave
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam("keyword") String keyword) {
        return postService.searchPosts(keyword);
    }
}
