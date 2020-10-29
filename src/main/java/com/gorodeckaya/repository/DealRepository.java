package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    Deal findById(long id);

}