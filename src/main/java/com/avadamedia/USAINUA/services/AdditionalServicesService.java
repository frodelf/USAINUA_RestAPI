package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.AdditionalService;

import java.util.List;

public interface AdditionalServicesService {
    void save(AdditionalService additionalService);
    AdditionalService getByName(String name);
    AdditionalService getById(long id);
    List<AdditionalService> getAll();
    void deleteById(long id);
}
