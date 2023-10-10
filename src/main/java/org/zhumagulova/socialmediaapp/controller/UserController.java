package org.zhumagulova.socialmediaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.model.UserFollow;
import org.zhumagulova.socialmediaapp.service.UserFollowService;
import org.zhumagulova.socialmediaapp.service.UserService;

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
