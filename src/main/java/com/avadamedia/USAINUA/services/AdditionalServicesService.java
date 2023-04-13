package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.AdditionalServices;
import com.avadamedia.USAINUA.repositories.AdditionalServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalServicesService {
    private final AdditionalServicesRepository additionalServicesRepository;

    public void save(AdditionalServices additionalServices){additionalServicesRepository.save(additionalServices);}
    public AdditionalServices getByName(String name){return additionalServicesRepository.findByName(name).get();}
    public AdditionalServices getById(long id){return additionalServicesRepository.findById(id).get();}
    public List<AdditionalServices> getAll(){return additionalServicesRepository.findAll();}
    public void deleteById(long id){additionalServicesRepository.deleteById(id);}
}
