package org.zhumagulova.chatgptlow.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.chatgptlow.model.UserFollow;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

}
