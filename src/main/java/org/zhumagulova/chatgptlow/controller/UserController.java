package org.zhumagulova.chatgptlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.User;
import org.zhumagulova.chatgptlow.model.UserFollow;
import org.zhumagulova.chatgptlow.service.UserFollowService;
import org.zhumagulova.chatgptlow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserFollowService userFollowService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{authorId}")
    public User getUser(@PathVariable Long authorId) {
        User author = userService.getUserById(authorId);
        return userService.getUserById(authorId);
    }

    @PostMapping("/{authorId}")
    public UserFollow followUser (@RequestParam Long myId, @PathVariable Long authorId) {
        return userFollowService.followUser (myId, authorId);
    }
    // Add more endpoints as needed
}
