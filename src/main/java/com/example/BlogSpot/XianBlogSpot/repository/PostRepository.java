package com.example.BlogSpot.XianBlogSpot.repository;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable; // Cambia esto
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> searchPosts(@Param("keyword") String keyword);

    // Método corregido
    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
