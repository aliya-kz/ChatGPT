package org.zhumagulova.socialmediaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.socialmediaapp.model.Post;
import org.zhumagulova.socialmediaapp.model.PostLike;
import org.zhumagulova.socialmediaapp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);

    List<PostLike> findByUser(User user);
}
