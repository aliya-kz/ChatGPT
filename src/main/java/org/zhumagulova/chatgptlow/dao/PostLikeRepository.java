package org.zhumagulova.chatgptlow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.chatgptlow.model.Post;
import org.zhumagulova.chatgptlow.model.PostLike;
import org.zhumagulova.chatgptlow.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);

    List<PostLike> findByUser(User user);
}
