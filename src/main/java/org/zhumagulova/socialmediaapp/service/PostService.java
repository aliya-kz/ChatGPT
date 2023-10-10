package org.zhumagulova.socialmediaapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.socialmediaapp.dao.PostRepository;
import org.zhumagulova.socialmediaapp.dao.UserRepository;
import org.zhumagulova.socialmediaapp.model.Post;
import org.zhumagulova.socialmediaapp.model.User;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Long authorId, Post post) {
        User author = userRepository.findById(authorId).orElseThrow();
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public List<Post> getPostsByAuthor(User author) {
        return postRepository.findByAuthor(author);
    }

}
