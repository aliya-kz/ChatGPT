package org.zhumagulova.socialmediaapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.socialmediaapp.model.UserFollow;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

}
