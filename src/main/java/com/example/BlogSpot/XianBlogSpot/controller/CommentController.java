package com.example.BlogSpot.XianBlogSpot.controller;

import com.example.BlogSpot.XianBlogSpot.model.Comment;
import com.example.BlogSpot.XianBlogSpot.model.Post;
import com.example.BlogSpot.XianBlogSpot.model.User;
import com.example.BlogSpot.XianBlogSpot.service.CommentService;
import com.example.BlogSpot.XianBlogSpot.service.PostService;
import com.example.BlogSpot.XianBlogSpot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    // Obtener todos los comentarios
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Crear un nuevo comentario y asociarlo a un post
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, Object> payload) {
        String content = (String) payload.get("content");
        Long postId = Long.valueOf(payload.get("postId").toString());
        Long authorId = Long.valueOf(payload.get("authorId").toString());

        // Buscamos el post en la base de datos
        Post post = postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si el post no existe
        }

        // Buscamos el autor en la base de datos
        User author = userService.findById(authorId);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si el autor no existe
        }

        // Creamos el comentario y lo asociamos al post y al autor
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);  // Asociar el post
        comment.setAuthor(author);  // Asociar el autor

        // Guardamos el comentario en la base de datos
        Comment savedComment = commentService.saveComment(comment);

        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    // Obtener comentarios por ID de post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Map<String, Object>>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        // Crear una lista de mapas para incluir los datos del comentario y el autor
        List<Map<String, Object>> response = comments.stream().map(comment -> {
            Map<String, Object> commentData = new HashMap<>();
            commentData.put("content", comment.getContent());
            commentData.put("authorName", comment.getAuthor().getUsername());
            return commentData;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
