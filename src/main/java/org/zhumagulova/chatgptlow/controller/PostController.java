package org.zhumagulova.chatgptlow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.chatgptlow.metrics.PostMetrics;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.User;
import org.zhumagulova.chatgptlow.service.PostLikeService;
import org.zhumagulova.chatgptlow.service.PostService;
import org.zhumagulova.chatgptlow.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    private final PostMetrics postMetrics;

    private final PostLikeService postLikeService;
    @Autowired
    public PostController(PostService postService, UserService userService, PostMetrics postMetrics, PostLikeService postLikeService) {
        this.postService = postService;
        this.userService = userService;
        this.postMetrics = postMetrics;
        this.postLikeService = postLikeService;
    }

    @PostMapping("/{authorId}")
    public Post createPost(@PathVariable Long authorId, @RequestBody Post post) {
        return postService.createPost(authorId, post);
    }

    @GetMapping("/{authorId}")
    public List<Post> getPostsByAuthor(@PathVariable Long authorId) {
        postMetrics.increment();
        User author = userService.getUserById(authorId);
        return postService.getPostsByAuthor(author);
    }
}
