package org.zhumagulova.chatgptlow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.chatgptlow.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom queries can be defined here
}

