package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Products;

import java.util.List;

public interface ProductsService {
    void save(Products products);
    List<Products> getAll();
    Products getByLink(String link);
    void deleteById(Long id);
    Products getById(long id);
    List<Products> getLast3();
}
