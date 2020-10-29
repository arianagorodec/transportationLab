package com.gorodeckaya.service.impl;

import com.gorodeckaya.entity.Partner;
import com.gorodeckaya.repository.PartnerRepository;
import com.gorodeckaya.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Partner addPartner(Partner partner) {
        return partnerRepository.saveAndFlush(partner);
    }

    public Partner updatePartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    @Override
    public void deleteById(long id) {
        partnerRepository.deleteById(id);
    }


    @Override
    public Partner getByEmail(String email) {
        return partnerRepository.findByEmail(email);
    }


    @Override
    public Partner editPartner(Partner partner) {
        return partnerRepository.saveAndFlush(partner);
    }

    @Override
    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    public Partner getInfoPartner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Partner partner = getByEmail(auth.getName());
        return partner;
    }
}
