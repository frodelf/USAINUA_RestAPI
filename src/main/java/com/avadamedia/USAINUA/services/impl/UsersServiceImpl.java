package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import com.avadamedia.USAINUA.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public void save(User user){usersRepository.save(user);}
    public User getById(long id){return usersRepository.findById(id).get();}
    public User getByEmail(String email){return usersRepository.findByEmail(email).get();}
    public List<User> getAllMan(){return usersRepository.findByIsManIsTrue();}
    public List<User> getAllWoman(){return usersRepository.findByIsManIsFalse();}
    public List<User> getAll(){return usersRepository.findAll();}
}
