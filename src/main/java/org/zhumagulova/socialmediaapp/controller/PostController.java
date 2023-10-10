package org.zhumagulova.socialmediaapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.socialmediaapp.metrics.PostMetrics;
import org.zhumagulova.socialmediaapp.model.Post;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.service.PostLikeService;
import org.zhumagulova.socialmediaapp.service.PostService;
import org.zhumagulova.socialmediaapp.service.UserService;

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
