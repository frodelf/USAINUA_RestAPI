package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Shops;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopsService {
    private final ShopsRepository shopsRepository;

    public void save(Shops shops){shopsRepository.save(shops);}
    public List<Shops> getAll(){return shopsRepository.findAll();}
    public Shops getByLink(String link){return shopsRepository.findByLink(link).get();}
    public Shops getById(long id){return shopsRepository.findById(id).get();}
    public void deleteById(long id){shopsRepository.deleteById(id);}
}
