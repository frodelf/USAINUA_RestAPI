package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> getAll();
    void save(Storage storage);
    void deleteById(long id);
    Storage getById(long id);
}
