package com.example.BlogSpot.XianBlogSpot.service;

import com.example.BlogSpot.XianBlogSpot.model.Comment;
import com.example.BlogSpot.XianBlogSpot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Método para obtener todos los comentarios
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Método para guardar un nuevo comentario
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
