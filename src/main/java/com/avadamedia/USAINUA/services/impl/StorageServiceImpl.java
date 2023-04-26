package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Storage;
import com.avadamedia.USAINUA.repositories.StorageRepository;
import com.avadamedia.USAINUA.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService{
    private final StorageRepository storageRepository;

    public List<Storage> getAll(){
        return storageRepository.findAll();
    }

    public void save(Storage storage){
        storageRepository.save(storage);}

    public void deleteById(long id){
        storageRepository.deleteById(id);
    }
    public Storage getById(long id){
        return storageRepository.findById(id).get();
    }
}
