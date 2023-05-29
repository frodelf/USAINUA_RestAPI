package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsersServiceImplTest {
    @InjectMocks
    private UsersServiceImpl usersService;
    @Mock
    private UsersRepository usersRepository;
    @Test
    void save() {
        User user = new User();
        usersService.save(user);
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    void getById() {
        User user = new User();
        user.setId(1L);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = usersService.getById(1L);
        assertEquals(user, result);
    }

    @Test
    void getByEmail() {
        User user = new User();
        user.setEmail("test@test.com");
        when(usersRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));
        User result = usersService.getByEmail("test@test.com");
        assertEquals(user, result);
    }

    @Test
    void getAllMan() {
        User user1 = new User();
        User user2 = new User();
        user1.setIsMan(true);
        user2.setIsMan(true);
        List<User> users = Arrays.asList(user1, user2);
        when(usersRepository.findByIsManIsTrue()).thenReturn(users);
        List<User> result = usersService.getAllMan();
        assertEquals(users, result);
    }

    @Test
    void getAllWoman() {
        User user1 = new User();
        User user2 = new User();
        user1.setIsMan(false);
        user2.setIsMan(false);
        List<User> users = Arrays.asList(user1, user2);
        when(usersRepository.findByIsManIsFalse()).thenReturn(users);
        List<User> result = usersService.getAllWoman();
        assertEquals(users, result);
    }

    @Test
    void getAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(usersRepository.findByIsManIsTrue()).thenReturn(users);
        List<User> result = usersService.getAllMan();
        assertEquals(users, result);
    }
}