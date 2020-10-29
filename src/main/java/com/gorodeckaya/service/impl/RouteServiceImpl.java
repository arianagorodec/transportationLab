package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Route;
import com.gorodeckaya.entity.TypeTransportation;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.repository.RouteRepository;
import com.gorodeckaya.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        return routeRepository.saveAndFlush(route);
    }

    public Route updateRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void deleteById(long id) {
        routeRepository.deleteById(id);
    }


    @Override
    public Route editRoute(Route route) {
        return routeRepository.saveAndFlush(route);
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> getAllbyFromTo(String from, String to) {
        return routeRepository.findByFromTo(from,to);
    }

    @Override
    public Route getbyFromToIdPartner(String from, String to, long id) {
        return routeRepository.findByFromToIdP(from,to,id);
    }

    @Override
    public List<String> getUniqFrom() {
        return routeRepository.findUniqFrom();
    }

    @Override
    public List<String> getUniqTo() {
        return routeRepository.findUniqTo();
    }
}
