package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Storage;
import com.avadamedia.USAINUA.repositories.StorageRepository;
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
class StorageServiceImplTest {
    @InjectMocks
    private StorageServiceImpl storageService;
    @Mock
    private StorageRepository storageRepository;
    @Test
    void getAll() {
        List<Storage> storageList = Arrays.asList(new Storage(), new Storage());
        when(storageRepository.findAll()).thenReturn(storageList);
        List<Storage> result = storageService.getAll();
        assertEquals(storageList, result);
        verify(storageRepository, times(1)).findAll();
    }
    @Test
    void save() {
        Storage storage = new Storage();
        storageService.save(storage);
        verify(storageRepository, times(1)).save(storage);
    }
    @Test
    void deleteById() {
        storageService.deleteById(1L);
        verify(storageRepository, times(1)).deleteById(1L);
    }
    @Test
    void getById() {
        Storage storage = new Storage();
        when(storageRepository.findById(1L)).thenReturn(Optional.of(storage));
        Storage result = storageService.getById(1L);
        assertEquals(storage, result);
        verify(storageRepository, times(1)).findById(1L);
    }
}