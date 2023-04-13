package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Users;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public void save(Users users){usersRepository.save(users);}
    public Users getById(long id){return usersRepository.findById(id).get();}
    public Users getByEmail(String email){return usersRepository.findByEmail(email).get();}
    public List<Users> getAllMan(){return usersRepository.findByIsManIsTrue();}
    public List<Users> getAllWoman(){return usersRepository.findByIsManIsFalse();}
    public List<Users> getAll(){return usersRepository.findAll();}


}
