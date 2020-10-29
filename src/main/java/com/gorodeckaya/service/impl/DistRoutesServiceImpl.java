package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Route;
import com.gorodeckaya.repository.DistRoutesRepository;
import com.gorodeckaya.repository.RouteRepository;
import com.gorodeckaya.service.DistRoutesService;
import com.gorodeckaya.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistRoutesServiceImpl implements DistRoutesService {
    @Autowired
    private DistRoutesRepository distRoutesRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public DistRoutes addDistRoutes(DistRoutes route) {
        return distRoutesRepository.saveAndFlush(route);
    }

    public DistRoutes updateDistRoutes(DistRoutes route) {
        return distRoutesRepository.save(route);
    }


    @Override
    public void deleteById(long id) {
        distRoutesRepository.deleteById(id);
    }


    @Override
    public DistRoutes editDistRoutes(DistRoutes route) {
        return distRoutesRepository.saveAndFlush(route);
    }

    @Override
    public List<DistRoutes> getAll() {
        return distRoutesRepository.findAll();
    }

    @Override
    public List<DistRoutes> findAllbyFromTo(String from, String to) {
        return distRoutesRepository.findAllFromTo(from, to);
    }

    @Override
    public DistRoutes findDistRoutesId(long id) {
        return distRoutesRepository.findById(id);
    }
}
