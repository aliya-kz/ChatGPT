package org.zhumagulova.chatgptlow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.PostLike;
import org.zhumagulova.chatgptlow.model.User;
import org.zhumagulova.chatgptlow.service.PostLikeService;
import org.zhumagulova.chatgptlow.service.PostService;
import org.zhumagulova.chatgptlow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private PostLikeService postLikeService;

    @PostMapping ("/{authorId}")
    public Post createPost(@PathVariable Long authorId, @RequestBody Post post) {
        return postService.createPost(authorId, post);
    }

    @GetMapping("/{authorId}")
    public List<Post> getPostsByAuthor(@PathVariable Long authorId) {
        User author = userService.getUserById(authorId);
        return postService.getPostsByAuthor(author);
    }


    // Add more endpoints as needed
}
