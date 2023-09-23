package org.zhumagulova.chatgptlow.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.chatgptlow.dao.PostLikeRepository;
import org.zhumagulova.chatgptlow.dao.PostRepository;
import org.zhumagulova.chatgptlow.dao.UserRepository;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.PostLike;
import org.zhumagulova.chatgptlow.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostLikeService {
    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void likePost(Long userId, long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + postId));

        // Check if the user has already liked the post
        if (postLikeRepository.findByUserAndPost(user, post).isEmpty()) {
            PostLike postLike = new PostLike();
            postLike.setUser(user);
            postLike.setPost(post);
            postLikeRepository.save(postLike);

            // You can also update the like count on the Post entity if needed
            // post.setLikeCount(post.getLikeCount() + 1);
            // postRepository.save(post);
        } else {
            // User has already liked the post; you can choose to handle this case differently
            throw new IllegalArgumentException("User has already liked this post.");
        }
    }

    // Method to unlike a post
    public void unlikePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + postId));

        // Check if the user has liked the post
        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("User has not liked this post."));

        postLikeRepository.delete(postLike);

        // You can also update the like count on the Post entity if needed
        // post.setLikeCount(post.getLikeCount() - 1);
        // postRepository.save(post);
    }

    // Method to get posts liked by a user
    public List<Post> getLikedPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        List<PostLike> likedPosts = postLikeRepository.findByUser(user);

        return likedPosts.stream()
                .map(PostLike::getPost)
                .collect(Collectors.toList());
    }
}
