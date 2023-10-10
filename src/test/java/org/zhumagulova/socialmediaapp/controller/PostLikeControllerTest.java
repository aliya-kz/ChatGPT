package org.zhumagulova.socialmediaapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.socialmediaapp.service.PostLikeService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostLikeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private PostLikeController postLikeController;

    @MockBean
    private PostLikeService postLikeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postLikeController).build();
    }

    @Test
    public void testLikePost() throws Exception {
        long userId = 1L;
        long postId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.post("/postlikes/{postId}", postId)
                        .param("userId", String.valueOf(userId))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(postLikeService, times(1)).likePost(userId, postId);
    }
}