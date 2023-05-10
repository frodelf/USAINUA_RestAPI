package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Shop;

import java.util.List;

public interface ShopsService {
    void save(Shop shop);
    List<Shop> getAll();
    Shop getByLink(String link);
    Shop getById(long id);
    void deleteById(long id);
}
