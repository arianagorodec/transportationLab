package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistRoutesRepository extends JpaRepository<DistRoutes, Long> {
    DistRoutes findById(long id);

    @Query("select a from DistRoutes a where a.route = :route")
    public List<DistRoutes> findAllbyIdRoute(@Param("route") Route route);
    @Query("select a from DistRoutes a where a.route.from = :from and a.route.to = :to")
    public List<DistRoutes> findAllFromTo(@Param("from") String from, @Param("to") String to);
}