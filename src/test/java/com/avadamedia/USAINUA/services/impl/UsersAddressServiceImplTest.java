package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsersAddressServiceImplTest {
    @InjectMocks
    private UsersAddressServiceImpl usersAddressService;
    @Mock
    private UsersAddressRepository usersAddressRepository;
    @Test
    void save() {
        UsersAddress usersAddress = new UsersAddress();
        usersAddressService.save(usersAddress);
        verify(usersAddressRepository, times(1)).save(usersAddress);
    }
    @Test
    void getById() {
        UsersAddress usersAddress = new UsersAddress();
        usersAddress.setId(1L);
        when(usersAddressRepository.findById(1L)).thenReturn(Optional.of(usersAddress));
        UsersAddress result = usersAddressService.getById(1);
        assertEquals(usersAddress, result);
    }
}