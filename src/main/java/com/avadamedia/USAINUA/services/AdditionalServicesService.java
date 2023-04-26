package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.AdditionalServices;

import java.util.List;

public interface AdditionalServicesService {
    void save(AdditionalServices additionalServices);
    AdditionalServices getByName(String name);
    AdditionalServices getById(long id);
    List<AdditionalServices> getAll();
    void deleteById(long id);
}
