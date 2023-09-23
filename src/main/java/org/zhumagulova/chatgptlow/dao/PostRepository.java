package org.zhumagulova.chatgptlow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);
    // Custom queries can be defined here
}