package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Products;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService{
    private final ProductsRepository productsRepository;

    public void save(Products products){productsRepository.save(products);}
    public List<Products> getAll(){return productsRepository.findAll();}
    public Products getByLink(String link){return productsRepository.findByLink(link).get();}
    public void deleteById(Long id){productsRepository.deleteById(id);}
    public Products getById(long id){return productsRepository.findById(id).get();}
}
