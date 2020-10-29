package com.gorodeckaya.service;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Deal;

import java.util.List;

public interface DealService {
    Deal addDeal(Deal deal);
    void deleteById(long id);
    Deal editDeal(Deal deal);
    List<Deal> getAll();
}
