package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Users;

import java.util.List;

public interface UsersService {
    void save(Users users);
    Users getById(long id);
    Users getByEmail(String email);
    List<Users> getAllMan();
    List<Users> getAllWoman();
    List<Users> getAll();
}
