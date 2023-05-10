package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import com.avadamedia.USAINUA.services.ShopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopsServiceImpl implements ShopsService {
    private final ShopsRepository shopsRepository;

    public void save(Shop shop){shopsRepository.save(shop);}
    public List<Shop> getAll(){return shopsRepository.findAll();}
    public Shop getByLink(String link){return shopsRepository.findByLink(link).get();}
    public Shop getById(long id){return shopsRepository.findById(id).get();}
    public void deleteById(long id){shopsRepository.deleteById(id);}
}
