package org.zhumagulova.socialmediaapp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zhumagulova.socialmediaapp.dao.PostRepository;
import org.zhumagulova.socialmediaapp.dao.UserRepository;
import org.zhumagulova.socialmediaapp.model.Post;
import org.zhumagulova.socialmediaapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePost_Success() {
        Long authorId = 1L;

        User author = new User();
        author.setId(authorId);

        Post post = new Post();
        post.setBody("Test post");

        when(userRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(postRepository.save(post)).thenReturn(post);

        Post createdPost = postService.createPost(authorId, post);

        assertNotNull(createdPost);
        assertEquals(author, createdPost.getAuthor());
        assertEquals("Test post", createdPost.getBody());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void testCreatePost_AuthorNotFound() {
        Long authorId = 1L;

        when(userRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            postService.createPost(authorId, new Post());
        });

        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    public void testGetPostsByAuthor() {
        Long authorId = 1L;

        User author = new User();
        author.setId(authorId);

        List<Post> authorPosts = new ArrayList<>();
        Post post1 = new Post();
        post1.setBody("Post 1");
        Post post2 = new Post();
        post2.setBody("Post 2");
        authorPosts.add(post1);
        authorPosts.add(post2);

        when(userRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(postRepository.findByAuthor(author)).thenReturn(authorPosts);

        List<Post> posts = postService.getPostsByAuthor(author);

        assertNotNull(posts);
        assertEquals(2, posts.size());
        assertEquals("Post 1", posts.get(0).getBody());
        assertEquals("Post 2", posts.get(1).getBody());
    }

    // Add more test methods as needed
}