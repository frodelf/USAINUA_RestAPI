package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.entity.UsersAddress;

public interface UsersAddressService {
    void save(UsersAddress usersAddress);
    UsersAddress getById(long id);
}
