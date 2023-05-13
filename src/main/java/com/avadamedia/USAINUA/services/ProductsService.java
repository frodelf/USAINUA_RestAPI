package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Product;

import java.util.List;

public interface ProductsService {
    void save(Product products);
    List<Product> getAll();
    Product getByLink(String link);
    void deleteById(Long id);
    Product getById(long id);
}
