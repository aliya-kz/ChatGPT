package org.zhumagulova.socialmediaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.socialmediaapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

