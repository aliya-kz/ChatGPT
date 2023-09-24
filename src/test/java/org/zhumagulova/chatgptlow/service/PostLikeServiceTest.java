package org.zhumagulova.chatgptlow.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zhumagulova.chatgptlow.dao.PostLikeRepository;
import org.zhumagulova.chatgptlow.dao.PostRepository;
import org.zhumagulova.chatgptlow.dao.UserRepository;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.PostLike;
import org.zhumagulova.chatgptlow.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostLikeServiceTest {

    @InjectMocks
    private PostLikeService postLikeService;

    @Mock
    private PostLikeRepository postLikeRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLikePost_Success() {
        Long userId = 1L;
        Long postId = 2L;

        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(postId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postLikeRepository.findByUserAndPost(user, post)).thenReturn(Optional.empty());

        postLikeService.likePost(userId, postId);

        verify(postLikeRepository, times(1)).save(any(PostLike.class));
    }

    @Test
    public void testLikePost_UserNotFound() {
        Long userId = 1L;
        Long postId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            postLikeService.likePost(userId, postId);
        });

        verify(postLikeRepository, never()).save(any(PostLike.class));
    }

    @Test
    public void testLikePost_PostNotFound() {
        Long userId = 1L;
        Long postId = 2L;

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            postLikeService.likePost(userId, postId);
        });

        verify(postLikeRepository, never()).save(any(PostLike.class));
    }

    @Test
    public void testLikePost_AlreadyLiked() {
        Long userId = 1L;
        Long postId = 2L;

        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(postId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postLikeRepository.findByUserAndPost(user, post)).thenReturn(Optional.of(new PostLike()));

        assertThrows(IllegalArgumentException.class, () -> {
            postLikeService.likePost(userId, postId);
        });

        verify(postLikeRepository, never()).save(any(PostLike.class));
    }

    // Additional tests for unlikePost and getLikedPosts methods can be added similarly.
}