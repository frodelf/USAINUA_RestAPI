package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.UsersAddress;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAddressService {
    private final UsersAddressRepository usersAddressRepository;

    public void save(UsersAddress usersAddress){usersAddressRepository.save(usersAddress);}
}
