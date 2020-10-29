package com.gorodeckaya.repository;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.TypeTransportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTransportationRepository extends JpaRepository<TypeTransportation, Long> {
    TypeTransportation findById(long id);

}