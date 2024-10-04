package com.example.BlogSpot.XianBlogSpot.repository;

import com.example.BlogSpot.XianBlogSpot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    // Eliminar comentarios por postId
    void deleteByPostId(Long postId);
}
