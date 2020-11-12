package com.gorodeckaya.service;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);
    void deleteById(long id);
    Route editRoute(Route route);
    List<Route> getAll();
    List<String> getUniqFrom();
    List<String> getUniqTo();
    List<Route> getAllbyFromTo(String from, String to);
    Route getbyFromToIdPartner(String from, String to, long id);
    Route findById(long id);
}
