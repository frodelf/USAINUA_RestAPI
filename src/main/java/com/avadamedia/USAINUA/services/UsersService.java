package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.User;

import java.util.List;

public interface UsersService {
    void save(User user);
    User getById(long id);
    User getByEmail(String email);
    List<User> getAllMan();
    List<User> getAllWoman();
    List<User> getAll();
}
