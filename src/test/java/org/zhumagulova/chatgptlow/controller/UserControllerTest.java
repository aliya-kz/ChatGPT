package org.zhumagulova.chatgptlow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.chatgptlow.model.User;
import org.zhumagulova.chatgptlow.service.UserFollowService;
import org.zhumagulova.chatgptlow.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private UserFollowService userFollowService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testUser");

        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"));

        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("user2"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUser() throws Exception {
        Long authorId = 1L;
        User user = new User();
        user.setId(authorId);
        user.setUsername("testUser");

        when(userService.getUserById(authorId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{authorId}", authorId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"));

        verify(userService, times(2)).getUserById(authorId);
    }

    @Test
    public void testFollowUser() throws Exception {
        Long myId = 2L;
        Long authorId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{authorId}", authorId)
                        .param("myId", myId.toString())
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userFollowService, times(1)).followUser(myId, authorId);
    }
}