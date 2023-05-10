package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.repositories.AdditionalServicesRepository;
import com.avadamedia.USAINUA.services.AdditionalServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalServicesServiceImpl implements AdditionalServicesService {
    private final AdditionalServicesRepository additionalServicesRepository;

    public void save(AdditionalService additionalService){additionalServicesRepository.save(additionalService);}
    public AdditionalService getByName(String name){return additionalServicesRepository.findByName(name).get();}
    public AdditionalService getById(long id){return additionalServicesRepository.findById(id).get();}
    public List<AdditionalService> getAll(){return additionalServicesRepository.findAll();}
    public void deleteById(long id){additionalServicesRepository.deleteById(id);}
}
