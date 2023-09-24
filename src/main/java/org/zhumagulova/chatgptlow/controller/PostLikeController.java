package org.zhumagulova.chatgptlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.chatgptlow.service.PostLikeService;

@RestController
@RequestMapping("/postlikes")
public class PostLikeController {
    @Autowired
    private PostLikeService postLikeService;
    @PostMapping("/{postId}")
    public void likePost(@RequestParam long userId, @PathVariable long postId) {
        postLikeService.likePost(userId, postId);
    }
}
