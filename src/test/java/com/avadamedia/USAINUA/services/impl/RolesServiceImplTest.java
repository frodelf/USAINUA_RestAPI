package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Role;
import com.avadamedia.USAINUA.repositories.RolesRepository;
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
class RolesServiceImplTest {
    @Mock
    private RolesRepository rolesRepository;
    @InjectMocks
    private RolesServiceImpl rolesService;
    @Test
    void getById() {
        Role role = new Role();
        role.setId(1L);
        when(rolesRepository.findById(1L)).thenReturn(Optional.of(role));
        Role result = rolesService.getById(1L);
        assertEquals(role, result);
    }

    @Test
    void save() {
        Role role = new Role();
        rolesService.save(role);
        verify(rolesRepository, times(1)).save(role);
    }
}