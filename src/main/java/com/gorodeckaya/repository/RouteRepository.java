package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findById(long id);

    @Query("select a from Route a where a.from = :from and a.to = :to")
    List<Route> findByFromTo(@Param("from") String from, @Param("to") String to);

    @Query("select distinct a.from from Route a")
    List<String> findUniqFrom();

    @Query("select distinct a.to from Route a")
    List<String> findUniqTo();

    @Query("select a from Route a where a.from = :from and a.to = :to and a.partner.id = :id")
    Route findByFromToIdP(@Param("from") String from, @Param("to") String to, @Param("id") long id);
}