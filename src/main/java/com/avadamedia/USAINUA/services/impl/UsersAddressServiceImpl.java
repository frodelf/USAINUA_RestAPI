package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import com.avadamedia.USAINUA.services.UsersAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAddressServiceImpl implements UsersAddressService {
    private final UsersAddressRepository usersAddressRepository;

    public void save(UsersAddress usersAddress){usersAddressRepository.save(usersAddress);}
    public UsersAddress getById(long id){return usersAddressRepository.findById(id).get();}
    public void deleteById(long id){
        usersAddressRepository.deleteById(id);
    }
}
