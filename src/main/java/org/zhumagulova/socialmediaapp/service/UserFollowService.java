package org.zhumagulova.socialmediaapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.socialmediaapp.dao.UserFollowRepository;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.model.UserFollow;

@Service
@AllArgsConstructor
public class UserFollowService {
    @Autowired
    private UserFollowRepository userFollowRepository;

    public UserFollow followUser(Long myId, Long authorId) {
        User follower = new User(myId);
        User following = new User(authorId);
        UserFollow userFollow = new UserFollow(follower, following);
        return userFollowRepository.save(userFollow);
    }
}
