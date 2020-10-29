package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.TypeTransportation;
import com.gorodeckaya.repository.TypeTransportationRepository;
import com.gorodeckaya.service.TypeTransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTransportationServiceImpl implements TypeTransportationService {
    @Autowired
    private TypeTransportationRepository typeTransportationRepository;

    @Override
    public TypeTransportation addTypeTransportation(TypeTransportation typeTransportation) {
        return typeTransportationRepository.saveAndFlush(typeTransportation);
    }

    public TypeTransportation updateTypeTransportation(TypeTransportation typeTransportation) {
        return typeTransportationRepository.save(typeTransportation);
    }

    @Override
    public void deleteById(long id) {
        typeTransportationRepository.deleteById(id);
    }


    @Override
    public TypeTransportation editTypeTransportation(TypeTransportation typeTransportation) {
        return typeTransportationRepository.saveAndFlush(typeTransportation);
    }

    @Override
    public List<TypeTransportation> getAll() {
        return typeTransportationRepository.findAll();
    }

}
