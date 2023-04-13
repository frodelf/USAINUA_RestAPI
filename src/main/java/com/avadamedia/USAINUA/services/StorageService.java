package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Storage;
import com.avadamedia.USAINUA.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    public void save(Storage storage){
        storageRepository.save(storage);}
}
