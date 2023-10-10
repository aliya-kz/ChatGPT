package org.zhumagulova.socialmediaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zhumagulova.socialmediaapp.dao.UserFollowRepository;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.model.UserFollow;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserFollowServiceTest {


    @InjectMocks
    private UserFollowService userFollowService;

    @Mock
    private UserFollowRepository userFollowRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFollowUser() {
        Long myId = 1L;
        Long authorId = 2L;

        // Create a mock UserFollow object
        UserFollow userFollow = new UserFollow(new User(myId), new User(authorId));

        // Mock the save method of the userFollowRepository
        when(userFollowRepository.save(any(UserFollow.class))).thenReturn(userFollow);

        // Call the followUser method
        UserFollow savedUserFollow = userFollowService.followUser(myId, authorId);

        // Verify that the save method was called with the correct UserFollow object
        verify(userFollowRepository, times(1)).save(any(UserFollow.class));

        // Assert that the returned UserFollow object matches the expected UserFollow
        assertEquals(myId, savedUserFollow.getFollower().getId());
        assertEquals(authorId, savedUserFollow.getFollowing().getId());
    }
}




