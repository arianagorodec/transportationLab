package com.gorodeckaya.service;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Route;

import java.util.List;

public interface DistRoutesService {
    DistRoutes addDistRoutes(DistRoutes distRoutes);
    void deleteById(long id);
    DistRoutes editDistRoutes(DistRoutes distRoutes);
    DistRoutes findDistRoutesId(long id);
    List<DistRoutes> getAll();
    List<DistRoutes> findAllbyFromTo(String from, String to);
}
