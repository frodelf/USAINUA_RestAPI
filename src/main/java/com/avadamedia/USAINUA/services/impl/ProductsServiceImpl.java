package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;

    public void save(Product products){productsRepository.save(products);}
    public List<Product> getAll(){return productsRepository.findAll();}
    public Product getByLink(String link){return productsRepository.findByLink(link).get();}
    public void deleteById(Long id){productsRepository.deleteById(id);}
    public Product getById(long id){return productsRepository.findById(id).get();}
    public List<Product> getLast3(){return productsRepository.findTop3ByOrderByIdDesc();}
}
