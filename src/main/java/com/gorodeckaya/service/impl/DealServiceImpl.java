package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.repository.DealRepository;
import com.gorodeckaya.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    @Autowired
    private DealRepository dealRepository;

    @Override
    public Deal addDeal(Deal deal) {
        return dealRepository.saveAndFlush(deal);
    }

    public Deal updateDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void deleteById(long id) {
        dealRepository.deleteById(id);
    }


    @Override
    public Deal editDeal(Deal deal) {
        return dealRepository.saveAndFlush(deal);
    }

    @Override
    public List<Deal> getAll() {
        return dealRepository.findAll();
    }

}
