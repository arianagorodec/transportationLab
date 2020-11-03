package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Deal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    Deal findById(long id);

    @Query("select distinct a from Deal a where a.distRoutes.route.partner.id= :id_partner")
    List<Deal> findDistinctByPartner(@Param("id_partner") long id);

    @Query("select a from Deal a where a.distRoutes.route.partner.id= :id_partner and " +
            "a.city_from= :from and a.city_to= :to")
    List<Deal> findByPartnerAndFromTo(@Param("id_partner") long id, @Param("from") String s, @Param("to") String s1);
}