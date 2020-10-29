package com.gorodeckaya.service;

import com.gorodeckaya.entity.Client;
import com.gorodeckaya.entity.Partner;

import java.util.List;

public interface PartnerService {
    Partner addPartner(Partner partner);
    void deleteById(long id);
    Partner editPartner(Partner partner);
    List<Partner> getAll();
    Partner getByEmail(String email);
}
