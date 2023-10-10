package org.zhumagulova.socialmediaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zhumagulova.socialmediaapp.dao.UserRepository;
import org.zhumagulova.socialmediaapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
    }

    @Test
    public void testGetUserById() {
        Long authorId = 1L;

        User user = new User();
        user.setId(authorId);
        user.setUsername("testUser");

        when(userRepository.findById(authorId)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(authorId);

        assertNotNull(retrievedUser);
        assertEquals(authorId, retrievedUser.getId());
        assertEquals("testUser", retrievedUser.getUsername());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Long authorId = 1L;

        when(userRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(authorId);
        });
    }

    // Add more test methods as needed
}