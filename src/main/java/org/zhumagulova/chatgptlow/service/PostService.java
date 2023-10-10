package org.zhumagulova.chatgptlow.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.chatgptlow.dao.PostRepository;
import org.zhumagulova.chatgptlow.dao.UserRepository;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.User;

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
