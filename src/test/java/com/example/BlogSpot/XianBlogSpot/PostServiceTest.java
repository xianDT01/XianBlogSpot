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
        @Test
        public void testGetPostById_NotFound() {
            when(postRepository.findById(1L)).thenReturn(Optional.empty());

            Post foundPost = postService.getPostById(1L);
            assertNull(foundPost);
        }
        @Test
        public void testDeletePostById() {
            // Tenemos un post con ID 1L
            Long postId = 1L;

            // Acción: llamamos al método deletePostById del servicio
            postService.deletePostById(postId);

            // Verificación: aseguramos que el método deleteById del repositorio se haya llamado una vez con el postId
            verify(postRepository, times(1)).deleteById(postId);
        }

        @Test
        public void testSavePost_CallsRepositorySave() {
            Post post = new Post();
            post.setTitle("Titulo");
            post.setContent("Contenido");
            post.setImageUrl("Imagen url");
            // Se llama al método savePost del servicio
            postService.savePost(post);
            // Se verifica que el método save() del repositorio fue llamado una vez con el post correcto
            verify(postRepository, times(1)).save(post);
        }
    }
