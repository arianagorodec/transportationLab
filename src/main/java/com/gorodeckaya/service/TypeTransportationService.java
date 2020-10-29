package com.gorodeckaya.service;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.TypeTransportation;

import java.util.List;

public interface TypeTransportationService {
    TypeTransportation addTypeTransportation(TypeTransportation typeTransportation);
    void deleteById(long id);
    TypeTransportation editTypeTransportation(TypeTransportation typeTransportation);
    List<TypeTransportation> getAll();
}
