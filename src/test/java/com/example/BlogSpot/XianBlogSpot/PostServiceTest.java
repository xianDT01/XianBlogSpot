    package com.example.BlogSpot.XianBlogSpot;

    import com.example.BlogSpot.XianBlogSpot.model.Post;
    import com.example.BlogSpot.XianBlogSpot.repository.PostRepository;
    import com.example.BlogSpot.XianBlogSpot.service.PostService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.MockitoAnnotations;

    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    public class PostServiceTest {

        @Mock
        private PostRepository postRepository;

        @InjectMocks
        private PostService postService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testSavePost() {
            Post post = new Post();
            post.setTitle("Titulo");
            post.setContent("Contenido");
            post.setImageUrl("Imagen url");

            when(postRepository.save(post)).thenReturn(post);

            Post savedPost = postService.savePost(post);
            assertNotNull(savedPost);
            assertEquals("Titulo", savedPost.getTitle());
            assertEquals("Contenido", savedPost.getContent());
            assertEquals("Imagen url", savedPost.getImageUrl());
        }
        @Test
        public void testGetPostById() {
            Post post = new Post();
            post.setId(1L);
            post.setTitle("Test1");

            when(postRepository.findById(1L)).thenReturn(Optional.of(post));

            Post foundPost = postService.getPostById(1L);
            assertNotNull(foundPost);
            assertEquals("Test1", foundPost.getTitle());
        }
    }
