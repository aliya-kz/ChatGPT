package org.zhumagulova.socialmediaapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zhumagulova.socialmediaapp.model.Post;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.service.PostLikeService;
import org.zhumagulova.socialmediaapp.service.PostService;
import org.zhumagulova.socialmediaapp.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @MockBean
    private PostLikeService postLikeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePost() throws Exception {
        Long authorId = 1L;
        User author = new User();
        author.setId(authorId);

        Post post = new Post();
        post.setBody("Test post");

        when(userService.getUserById(authorId)).thenReturn(author);
        when(postService.createPost(authorId, post)).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts/{authorId}", authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Test post"));

        verify(postService, times(1)).createPost(authorId, post);
    }

    @Test
    public void testGetPostsByAuthor() throws Exception {
        Long authorId = 1L;
        User author = new User();
        author.setId(authorId);

        Post post1 = new Post();
        post1.setBody("Post 1");

        Post post2 = new Post();
        post2.setBody("Post 2");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        when(userService.getUserById(authorId)).thenReturn(author);
        when(postService.getPostsByAuthor(author)).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{authorId}", authorId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].body").value("Post 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].body").value("Post 2"));

        verify(userService, times(1)).getUserById(authorId);
        verify(postService, times(1)).getPostsByAuthor(author);
    }
}