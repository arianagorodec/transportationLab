package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(long id);

    Client findByEmail(String email);

}