package org.zhumagulova.chatgptlow.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.chatgptlow.dao.UserFollowRepository;
import org.zhumagulova.chatgptlow.model.UserFollow;

@Service
@AllArgsConstructor
public class UserFollowService {
    @Autowired
    private UserFollowRepository userFollowRepository;

    public void followUser(Long myId, Long authorId) {
        UserFollow userFollow = new UserFollow(myId, authorId);
        userFollowRepository.save(userFollow);
    }
}
