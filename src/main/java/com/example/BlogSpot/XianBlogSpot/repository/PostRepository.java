package com.example.BlogSpot.XianBlogSpot.repository;

import com.example.BlogSpot.XianBlogSpot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
