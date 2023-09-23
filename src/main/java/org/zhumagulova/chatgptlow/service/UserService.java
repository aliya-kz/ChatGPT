package org.zhumagulova.chatgptlow.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.chatgptlow.dao.UserRepository;
import org.zhumagulova.chatgptlow.model.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long authorId) {
        return userRepository.findById(authorId).get();
    }

    // Add more methods as needed
}

