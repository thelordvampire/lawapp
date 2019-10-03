package com.app.law.repository;

import com.app.law.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUsernameAndPassword(String username, String password);

    User findOneByEmail(String email);

    @Query(value = "select u from user u where u.roleId = ?1")
    List<User> findByType(Integer type);

}

