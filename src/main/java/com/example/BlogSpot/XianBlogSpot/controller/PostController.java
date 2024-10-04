package com.example.BlogSpot.XianBlogSpot.controller;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.repository.CommentRepository;
import com.example.BlogSpot.XianBlogSpot.repository.PostRepository;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

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
    // Editar un post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post post = postService.getPostById(id);

        if (post != null) {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setImageUrl(updatedPost.getImageUrl()); // Si también se permite actualizar la imagen

            Post savedPost = postService.savePost(post); // Guardar el post actualizado
            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Borrar un post
// Borrar un post
    @DeleteMapping("/{id}") // Cambia esto
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            // Eliminar todos los comentarios asociados al post
            commentRepository.deleteByPostId(post.getId());

            // Eliminar el post
            postRepository.delete(post);

            return ResponseEntity.ok().build(); // Retornar respuesta 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retornar respuesta 404 si el post no se encuentra
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
    public ResponseEntity<Page<Post>> searchPosts(@RequestParam String keyword,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        return ResponseEntity.ok(posts);
    }
}
