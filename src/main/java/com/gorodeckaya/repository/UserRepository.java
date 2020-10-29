package com.gorodeckaya.repository;

import com.gorodeckaya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(long id);

    @Query("select a from User a where a.role = 1")
    List<User> findAdmin();

}